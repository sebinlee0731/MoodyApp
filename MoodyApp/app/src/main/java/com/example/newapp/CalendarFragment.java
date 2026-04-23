package com.example.newapp;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalendarFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private TextView textEmotion, textDiary;
    private ImageButton buttonEdit;
    private final Map<String, EmotionEntry> emotionMap = new HashMap<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        calendarView = view.findViewById(R.id.calendarView);
        textEmotion = view.findViewById(R.id.textEmotion);
        textDiary = view.findViewById(R.id.textDiary);
        buttonEdit = view.findViewById(R.id.buttonEdit);

        // 날짜 선택 시 동작
        calendarView.setOnDateChangedListener((widget, date, selected) -> updateSelectedDate(date));

        // 편집 버튼 클릭 시 감정/일기 작성 팝업
        buttonEdit.setOnClickListener(v -> {
            CalendarDay selectedDate = calendarView.getSelectedDate();
            if (selectedDate != null) {
                showEmotionDialog(selectedDate);
            }
        });

        // 주말 색상 데코레이터 적용
        calendarView.addDecorator(new SundayDecorator());
        calendarView.addDecorator(new SaturdayDecorator());

        // 초기 날짜 선택
        CalendarDay today = CalendarDay.today();
        calendarView.setDateSelected(today, true);
        updateSelectedDate(today);

        // ↓↓↓ DB에서 기존 감정 데이터를 불러오는 구조 예시 ↓↓↓
        // loadEmotionDataFromDatabase();
    }

    private void updateSelectedDate(CalendarDay date) {
        String key = date.getDate().toString();

        if (emotionMap.containsKey(key)) {
            EmotionEntry entry = emotionMap.get(key);
            textEmotion.setText("감정: " + entry.emotion);
            textDiary.setText("일기: " + entry.diary);
        } else {
            textEmotion.setText("감정: 없음");
            textDiary.setText("일기: 없음");
        }

        // 데코레이터 초기화
        calendarView.removeDecorators();
        calendarView.addDecorator(new SundayDecorator());
        calendarView.addDecorator(new SaturdayDecorator());

        // 날짜마다 감정에 따른 배경색 표시
        for (Map.Entry<String, EmotionEntry> e : emotionMap.entrySet()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate localDate = LocalDate.parse(e.getKey());
                CalendarDay cd = CalendarDay.from(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
                calendarView.addDecorator(new SingleEmotionDecorator(cd, e.getValue().emotion));
            }
        }
    }

    private void showEmotionDialog(CalendarDay date) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_emotion, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        EditText editTextDiary = dialogView.findViewById(R.id.editTextDiary);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        Button buttonSave = dialogView.findViewById(R.id.buttonSave);

        String[] selectedEmotion = {null};

        int[] emotionIds = {
                R.id.emotion_happy, R.id.emotion_calm, R.id.emotion_okay,
                R.id.emotion_sad, R.id.emotion_stressed
        };

        String[] emotionNames = {"행복", "차분", "보통", "슬픔", "스트레스"};
        ImageButton[] emotionButtons = new ImageButton[emotionIds.length];

        for (int i = 0; i < emotionIds.length; i++) {
            int index = i;
            emotionButtons[i] = dialogView.findViewById(emotionIds[i]);
            emotionButtons[i].setOnClickListener(v -> {
                selectedEmotion[0] = emotionNames[index];
                for (int j = 0; j < emotionButtons.length; j++) {
                    emotionButtons[j].setBackgroundResource(j == index ? R.drawable.emotion_selected_bg : 0);
                }
            });
        }

        buttonCancel.setOnClickListener(v -> dialog.dismiss());

        buttonSave.setOnClickListener(v -> {
            String diaryText = editTextDiary.getText().toString();
            if (selectedEmotion[0] != null && !diaryText.isEmpty()) {
                String key = date.getDate().toString();
                emotionMap.put(key, new EmotionEntry(selectedEmotion[0], diaryText));
                updateSelectedDate(date);

                // ↓↓↓ 감정/일기 DB에 저장하는 구조 예시 ↓↓↓
                // saveEmotionToDatabase(key, selectedEmotion[0], diaryText);

                dialog.dismiss();
            } else {
                Toast.makeText(getContext(), "감정과 일기를 모두 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    // 감정 데이터 모델 클래스
    public static class EmotionEntry {
        String emotion;
        String diary;

        public EmotionEntry(String emotion, String diary) {
            this.emotion = emotion;
            this.diary = diary;
        }
    }

    // 감정에 따른 배경색 표시 데코레이터
    public class SingleEmotionDecorator implements DayViewDecorator {
        private final CalendarDay date;
        private final String emotion;

        public SingleEmotionDecorator(CalendarDay date, String emotion) {
            this.date = date;
            this.emotion = emotion;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            int color;
            switch (emotion) {
                case "행복": color = Color.YELLOW; break;
                case "차분": color = Color.CYAN; break;
                case "보통": color = Color.LTGRAY; break;
                case "슬픔": color = 0xFFD1C4E9; break;
                case "스트레스": color = Color.RED; break;
                default: color = Color.WHITE; break;
            }
            view.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    // 일요일 빨간 글자
    public class SundayDecorator implements DayViewDecorator {
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new android.text.style.ForegroundColorSpan(Color.RED));
        }
    }

    // 토요일 파란 글자
    public class SaturdayDecorator implements DayViewDecorator {
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new android.text.style.ForegroundColorSpan(Color.BLUE));
        }
    }

    // ↓↓↓ DB에서 감정 데이터를 불러오는 구조 예시 ↓↓↓
    /*
    private void loadEmotionDataFromDatabase() {
        // DB 연결
        // SELECT * FROM emotions WHERE user_id = currentUser
        // for each row: emotionMap.put(dateString, new EmotionEntry(emotion, diary));
    }

    private void saveEmotionToDatabase(String dateKey, String emotion, String diary) {
        // DB 연결
        // INSERT OR REPLACE INTO emotions (user_id, date, emotion, diary) VALUES (...)
    }
    */
}