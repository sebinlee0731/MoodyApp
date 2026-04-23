package com.example.newapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MonthlyStatsActivity extends AppCompatActivity {

    private LinearLayout chartContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_stats);

        chartContainer = findViewById(R.id.chartContainer);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        // TODO: DB에서 감정 데이터를 불러와 emotionCounts를 구성
        // Map<String, Integer> emotionCounts = getEmotionCountsFromDB();

        // 예시: DB에서 가져온 감정 데이터가 아래처럼 구성되었다고 가정
        // emotionCounts.put("행복", 12);
        // emotionCounts.put("슬픔", 5);
        // emotionCounts.put("분노", 3);
        // emotionCounts.put("불안", 7);
        // emotionCounts.put("평온", 9);

        // 차트를 그리는 로직은 DB 연동 완료 후 emotionCounts 데이터를 기반으로 사용
        // drawChart(emotionCounts);
    }

    // 차트를 그리는 메서드 (DB 연동 후 호출)
    private void drawChart(Map<String, Integer> emotionCounts) {
        chartContainer.removeAllViews();

        int maxCount = 0;
        for (int count : emotionCounts.values()) {
            if (count > maxCount) maxCount = count;
        }

        for (Map.Entry<String, Integer> entry : emotionCounts.entrySet()) {
            String emotion = entry.getKey();
            int count = entry.getValue();

            float weight = (maxCount > 0) ? ((float) count / maxCount) : 0f;

            LinearLayout barLayout = new LinearLayout(this);
            barLayout.setOrientation(LinearLayout.HORIZONTAL);
            barLayout.setPadding(0, 16, 0, 16);

            TextView label = new TextView(this);
            label.setText(emotion);
            label.setTextSize(16f);
            label.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView bar = new TextView(this);
            bar.setBackgroundColor(Color.BLUE);
            bar.setHeight(24);
            bar.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight));
            bar.setText(" " + count);
            bar.setTextColor(Color.WHITE);

            barLayout.addView(label);
            barLayout.addView(bar);

            chartContainer.addView(barLayout);
        }
    }

    // TODO: DB에서 감정 통계 데이터를 가져오는 함수
    // private Map<String, Integer> getEmotionCountsFromDB() {
    //     Map<String, Integer> result = new HashMap<>();
    //     // DB 연결 및 쿼리 실행
    //     // 예: SELECT emotion, COUNT(*) FROM EmotionTable WHERE date BETWEEN 'start' AND 'end' GROUP BY emotion
    //     return result;
    // }
}