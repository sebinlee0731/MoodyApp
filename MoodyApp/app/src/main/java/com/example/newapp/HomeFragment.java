package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private int selectedEmotionId = -1;
    private Button btnPlace, btnMusic;
    private int[] emotionIds = {
            R.id.emotion_happy,
            R.id.emotion_calm,
            R.id.emotion_okay,
            R.id.emotion_sad,
            R.id.emotion_stressed
    };

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 감정 버튼 설정
        for (int i = 0; i < emotionIds.length; i++) {
            int index = i;
            ImageButton btn = view.findViewById(emotionIds[i]);
            btn.setOnClickListener(v -> setEmotion(index, view));
        }

        // 추천 버튼
        btnPlace = view.findViewById(R.id.button_place);
        btnMusic = view.findViewById(R.id.button_music);

        btnPlace.setOnClickListener(v -> {
            if (selectedEmotionId != -1) {
                Toast.makeText(getContext(), "장소 추천 (감정 ID: " + selectedEmotionId + ")", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "감정을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        btnMusic.setOnClickListener(v -> {
            if (selectedEmotionId != -1) {
                Toast.makeText(getContext(), "음악 추천 (감정 ID: " + selectedEmotionId + ")", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "감정을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setEmotion(int emotionId, View rootView) {
        selectedEmotionId = emotionId;

        // 모든 버튼 배경 초기화 & 선택 버튼 강조
        for (int i = 0; i < emotionIds.length; i++) {
            ImageButton btn = rootView.findViewById(emotionIds[i]);
            if (i == emotionId) {
                btn.setBackgroundResource(R.drawable.emotion_selected);
            } else {
                btn.setBackground(null);
            }
        }

        Toast.makeText(getContext(), "감정 선택됨: " + getEmotionName(emotionId), Toast.LENGTH_SHORT).show();
    }

    private String getEmotionName(int id) {
        switch (id) {
            case 0: return "행복";
            case 1: return "차분";
            case 2: return "보통";
            case 3: return "슬픔";
            case 4: return "스트레스";
            default: return "알 수 없음";
        }
    }
}