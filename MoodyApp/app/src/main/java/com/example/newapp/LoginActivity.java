package com.example.newapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextId, editTextPassword;
    private CheckBox checkboxAutoLogin, checkboxSaveId;
    private Button buttonLogin;
    private TextView textSignup;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextId = findViewById(R.id.editTextId);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textSignup = findViewById(R.id.textSignup);
        checkboxAutoLogin = findViewById(R.id.checkboxAutoLogin);
        checkboxSaveId = findViewById(R.id.checkboxSaveId);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String savedId = sharedPreferences.getString("saved_id", "");
        boolean autoLogin = sharedPreferences.getBoolean("auto_login", false);
        boolean saveId = sharedPreferences.getBoolean("save_id", false);

        if (saveId) editTextId.setText(savedId);
        checkboxAutoLogin.setChecked(autoLogin);
        checkboxSaveId.setChecked(saveId);

        // ✅ 자동 로그인 처리 (DB 연동된 유효 계정인지는 이후 로직에서 판단 필요)
        if (autoLogin && !savedId.isEmpty()) {
            // TODO: 여기에 DB에서 savedId가 존재하고 유효한 계정인지 확인하는 코드 필요
            // 예: if (checkValidUserFromDB(savedId)) { ... }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        buttonLogin.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            String pw = editTextPassword.getText().toString().trim();

            if (id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ DB 연동을 통한 로그인 검증
            // TODO: 여기에 DB에서 입력한 id, pw를 검증하는 로직 추가
            // 예:
            // boolean isValidUser = checkLoginFromDB(id, pw);
            // if (isValidUser) { ... }

            // 아래는 임시로 항상 로그인 실패 처리 (테스트용)
            boolean isValidUser = false;

            // TODO: 실제 DB 로그인 성공 여부에 따라 isValidUser 값을 설정
            // 예시: isValidUser = dbHelper.login(id, pw);

            if (isValidUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (checkboxSaveId.isChecked()) {
                    editor.putString("saved_id", id);
                    editor.putBoolean("save_id", true);
                } else {
                    editor.remove("saved_id");
                    editor.putBoolean("save_id", false);
                }

                editor.putBoolean("auto_login", checkboxAutoLogin.isChecked());
                editor.apply();

                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        textSignup.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
    }

    // TODO: 아래에 DB에서 사용자 인증을 수행하는 메서드를 작성해야 함
    // 예시:
    // private boolean checkLoginFromDB(String id, String pw) {
    //     // SQLite 또는 서버와 연결하여 사용자 인증 수행
    //     // SELECT * FROM Users WHERE userId = ? AND password = ?
    //     // 인증 성공 시 true 반환, 실패 시 false
    //     return false;
    // }

    // TODO: 자동 로그인 시 사용자 유효성 확인 메서드 예시
    // private boolean checkValidUserFromDB(String id) {
    //     // SELECT * FROM Users WHERE userId = ?
    //     return true or false;
    // }
}