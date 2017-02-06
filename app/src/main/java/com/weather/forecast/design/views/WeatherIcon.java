package com.weather.forecast.design.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Владелец on 03.02.2017.
 */

public class WeatherIcon extends TextView {
    public WeatherIcon(Context context) {
        super(context);
        init();
    }

    public WeatherIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "weathericons.ttf");
        setTypeface(tf);
    }
}
