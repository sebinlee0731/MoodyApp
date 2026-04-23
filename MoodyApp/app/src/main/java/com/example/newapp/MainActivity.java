package com.example.newapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment HomeFragment;
    Fragment calendarFragment;
    Fragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        HomeFragment = new HomeFragment();
        calendarFragment = new CalendarFragment();
        profileFragment = new ProfileFragment();

        // 처음 시작 시 홈 프래그먼트 표시
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                Fragment fragment = null;

                if (itemId == R.id.menu_home) {
                    fragment = HomeFragment;

                } else if (itemId == R.id.menu_calendar) {
                    fragment = calendarFragment;

                } else if (itemId == R.id.menu_profile) {
                    fragment = profileFragment;
                }

                return loadFragment(fragment);
            }

            boolean loadFragment(Fragment fragment) {
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
