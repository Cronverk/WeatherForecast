package com.weather.forecast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.weather.forecast.design.views.WeatherIcon;
import com.weather.forecast.entity.Forecast;
import com.weather.forecast.design.fragments.WeatherFragment;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Владелец on 02.02.2017.
 */

public class WeatherCardAdapter extends FragmentPagerAdapter{

    ArrayList<Forecast> forecasts;
    ArrayList<String> titles;

    public WeatherCardAdapter(FragmentManager fm, ArrayList<Forecast> forecasts) {
        super(fm);
        this.forecasts = forecasts;
        titles = new ArrayList<>();

        for(int i=0; i < 5; i++)
            titles.add(new String());

    }

    @Override
    public int getCount() {
        return forecasts.size();
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(forecasts.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(forecasts.get(position).getList().get(0).getDtTxt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat =new SimpleDateFormat("E d", Locale.ENGLISH);

        return dateFormat.format(date);
    }

    public void updateForecasts(ArrayList forecasts) {
        this.forecasts = forecasts;
    }
/*
    public View getTabView(int position) {


        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(forecasts.get(position).getList().get(0).getDtTxt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat =new SimpleDateFormat("E d", Locale.ENGLISH);

        View tab = LayoutInflater.from(getC.inflate(R.layout.tab, null);
        WeatherIcon icon = (WeatherIcon) tab.findViewById(R.id.tab_icon);
        TextView tab_text = (TextView) tab.findViewById(R.id.tab_text);
        icon.setText("\uF01D \n");
        tab_text.setText(""+dateFormat.format(date));

        return tab;
    }*/
}
