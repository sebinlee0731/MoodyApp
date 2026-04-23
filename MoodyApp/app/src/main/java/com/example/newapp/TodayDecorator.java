package com.example.newapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class TodayDecorator implements DayViewDecorator {

    private final CalendarDay today;
    private final Drawable backgroundDrawable;

    public TodayDecorator(Context context, String emotion) {
        today = CalendarDay.today();

        int color;
        switch (emotion.toLowerCase()) {
            case "happy":
                color = Color.WHITE;
                break;
            case "calm":
                color = Color.parseColor("#ADD8E6"); // 하늘색
                break;
            case "okay":
                color = Color.YELLOW;
                break;
            case "sad":
                color = Color.parseColor("#D8BFD8"); // 연보라색
                break;
            case "stressed":
                color = Color.RED;
                break;
            default:
                color = Color.LTGRAY; // 기본값
        }

        backgroundDrawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(today);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(backgroundDrawable);
    }
}
