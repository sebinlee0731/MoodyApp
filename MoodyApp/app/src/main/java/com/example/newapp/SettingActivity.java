package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private EditText editPassword, editAddress;
    private TextView textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        textId = findViewById(R.id.textId);
        editPassword = findViewById(R.id.editPassword);
        editAddress = findViewById(R.id.editAddress);

        // TODO: DB에서 사용자 정보 가져오기

        Button buttonApply = findViewById(R.id.buttonApply);
        buttonApply.setOnClickListener(v -> {
            // TODO: 비밀번호, 주소 DB에 업데이트
            Toast.makeText(this, "설정이 적용되었습니다.", Toast.LENGTH_SHORT).show();
        });

        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> {
            // TODO: 로그아웃 처리
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }
}
