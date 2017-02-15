package com.weather.forecast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weather.forecast.entity.Forecast;
import com.weather.forecast.design.fragments.WeatherFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class WeatherCardAdapter extends FragmentPagerAdapter{

    Forecast forecast;

    public WeatherCardAdapter(FragmentManager fm, Forecast forecast) {
        super(fm);
        this.forecast = forecast;
    }

    @Override
    public int getCount() {
        return forecast.getWeatherByDays().size();
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(forecast,position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return forecast.getWeatherByDays().get(position).getDayInfo();
    }

    public void updateForecasts(Forecast forecast) {
        this.forecast = forecast;
    }
}
