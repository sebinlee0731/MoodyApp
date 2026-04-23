package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // 설정 버튼 클릭 시 SettingActivity로 이동
        Button settingButton = view.findViewById(R.id.buttonSetting);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SettingActivity.class));
        });

        // See All 버튼 클릭 시 MonthlyStatsActivity로 이동
        Button seeAllButton = view.findViewById(R.id.buttonSeeAll);
        seeAllButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MonthlyStatsActivity.class));
        });

        return view;
    }
}