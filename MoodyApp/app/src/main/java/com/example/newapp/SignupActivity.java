package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextId, editTextPassword, editTextConfirmPassword, editTextAddress;
    private TextView textPasswordWarning, textMismatchWarning;
    private ImageView imageCheck;
    private Button buttonSignup;

    private Set<String> existingIds = new HashSet<>(Arrays.asList("user1", "testuser")); // 예시 아이디 목록

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextId = findViewById(R.id.editTextId);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextAddress = findViewById(R.id.editTextAddress);
        textPasswordWarning = findViewById(R.id.textPasswordWarning);
        textMismatchWarning = findViewById(R.id.textMismatchWarning);
        imageCheck = findViewById(R.id.imageCheck);
        buttonSignup = findViewById(R.id.buttonSignup);

        // 아이디 중복 자동 확인
        editTextId.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (existingIds.contains(s.toString())) {
                    imageCheck.setVisibility(View.GONE);
                } else {
                    imageCheck.setVisibility(View.VISIBLE);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        // 회원가입 버튼 클릭
        buttonSignup.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            String pw = editTextPassword.getText().toString().trim();
            String pwConfirm = editTextConfirmPassword.getText().toString().trim();
            String address = editTextAddress.getText().toString().trim();

            boolean valid = true;

            // 비밀번호 조건 확인
            if (!pw.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                textPasswordWarning.setVisibility(View.VISIBLE);
                valid = false;
            } else {
                textPasswordWarning.setVisibility(View.GONE);
            }

            // 비밀번호 일치 확인
            if (!pw.equals(pwConfirm)) {
                textMismatchWarning.setVisibility(View.VISIBLE);
                valid = false;
            } else {
                textMismatchWarning.setVisibility(View.GONE);
            }

            // 최종 조건 통과 시 메인 화면 이동
            if (valid) {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}