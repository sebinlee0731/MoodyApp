package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView loadingImageView;

    // 로딩 이미지 배열
    private final int[] loadingImages = {
            R.drawable.loading_1,
            R.drawable.loading_2,
            R.drawable.loading_3
    };

    private int imageIndex = 0;
    private final int IMAGE_INTERVAL = 500; // 0.5초마다 이미지 변경

    private final Handler imageHandler = new Handler(Looper.getMainLooper());
    private final Runnable imageRunnable = new Runnable() {
        @Override
        public void run() {
            loadingImageView.setImageResource(loadingImages[imageIndex]);
            imageIndex = (imageIndex + 1) % loadingImages.length;
            imageHandler.postDelayed(this, IMAGE_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading); // 로딩 화면 레이아웃

        loadingImageView = findViewById(R.id.loadingImageView);
        imageHandler.post(imageRunnable); // 이미지 애니메이션 시작

        // 3초 후 로그인 화면으로 이동
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            imageHandler.removeCallbacks(imageRunnable); // 이미지 전환 멈춤
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, 3000);
    }
}