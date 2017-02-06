package com.weather.forecast.design.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.forecast.R;
import com.weather.forecast.design.views.WeatherIcon;
import com.weather.forecast.entity.Forecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Владелец on 02.02.2017.
 */

public class WeatherFragment extends Fragment{

    @BindView(R.id.weather_icon)WeatherIcon icon;
    @BindView(R.id.date)        TextView dateView;
    @BindView(R.id.city)        TextView cityView;
    @BindView(R.id.temperature) TextView tempView;
    @BindView(R.id.humidity)    TextView humidityView;
    @BindView(R.id.pressure)    TextView pressureView;
    @BindView(R.id.wind)        TextView windView;

    @BindString(R.string.humidity_text) String humidity_text;
    @BindString(R.string.pressure_text) String pressure_text;
    @BindString(R.string.temperature_text) String temp_text;
    @BindString(R.string.wind_text)     String wind_text;


    public static WeatherFragment newInstance(Forecast forecast) {
        
        Bundle args = new Bundle();
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(forecast.getList().get(0).getDtTxt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat =new SimpleDateFormat("E d", Locale.ENGLISH);

        args.putString("city",forecast.getCity().toString());
        args.putString("date",dateFormat.format(date));
        args.putDouble("humidity", forecast.getList().get(0).getMain().getHumidity());
        args.putDouble("pressure", forecast.getList().get(0).getMain().getPressure());
        args.putDouble("temp",forecast.getList().get(0).getMain().getTemp());
        args.putDouble("wind", forecast.getList().get(0).getWind().getSpeed());
        args.putInt("icon",forecast.getList().get(0).getWeather().get(0).getId());

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        View  rootView = inflater.inflate(R.layout.weather_card, container, false);
        ButterKnife.bind(this, rootView);


        icon.setText(weatherIcons.get(args.getInt("icon")));

        dateView.setText(args.getString("date"));
        cityView.setText(args.getString("city"));


        tempView.setText(String.format(temp_text, roundDouble(args.getDouble("temp")-273.15)));
        humidityView.setText(String.format(humidity_text, roundDouble(args.getDouble("humidity"))));
        pressureView.setText(String.format(pressure_text, roundDouble(args.getDouble("pressure"))));
        windView.setText(String.format(wind_text, roundDouble(args.getDouble("wind"))));

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    private static final Map<Integer, String> weatherIcons = Collections.unmodifiableMap(
            new HashMap<Integer, String>() {{
                put(200,"\uf01d");
                put(201,"\uf01d");
                put(202,"\uf01d");
                put(210,"\uf01d");
                put(230,"\uf01d");
                put(231,"\uf01d");
                put(232,"\uf01d");
                put(211,"\uf01e");
                put(212,"\uf01e");
                put(221,"\uf01e");
                put(300,"\uf01c");
                put(301,"\uf01c");
                put(302,"\uf01c");
                put(310,"\uf01c");
                put(311,"\uf01c");
                put(312,"\uf01c");
                put(313,"\uf01c");
                put(314,"\uf01c");
                put(321,"\uf01c");
                put(500,"\uf01b");
                put(501,"\uf019");
                put(502,"\uf019");
                put(503,"\uf019");
                put(504,"\uf019");
                put(511,"\uf017");
                put(615,"\uf017");
                put(616,"\uf017");
                put(620,"\uf017");
                put(621,"\uf017");
                put(622,"\uf017");
                put(520,"\uf01a");
                put(521,"\uf01a");
                put(522,"\uf01a");
                put(531,"\uf01a");
                put(600,"\uf01b");
                put(601,"\uf01b");
                put(602,"\uf01b");
                put(611,"\uf0b5");
                put(612,"\uf0b5");
                put(701,"\uf01c");
                put(711,"\uf062");
                put(721,"\uf0b6");
                put(731,"\uf011");
                put(741,"\uf014");
                put(751,"\uf011");
                put(761,"\uf063");
                put(762,"\uf074");
                put(771,"\uf085");
                put(781,"\uf056");
                put(900,"\uf056");
                put(800,"\uf00d");
                put(951,"\uf00d");
                put(801,"\uf013");
                put(802,"\uf013");
                put(803,"\uf013");
                put(804,"\uf013");
                put(901,"\uf073");
                put(902,"\uf073");
                put(903,"\uf076");
                put(904,"\uf072");
                put(905,"\uf021");
                put(906,"\uf015");
                put(952,"\uf011");
                put(953,"\uf011");
                put(954,"\uf011");
                put(955,"\uf011");
                put(956,"\uf011");
                put(957,"\uf011");
                put(958,"\uf011");
                put(959,"\uf011");
                put(962,"\uf011");
                put(960,"\uf01e");
                put(961,"\uf01e");

            }});

    public String roundDouble(double value){
        StringBuffer str_value = new StringBuffer(""+value);
        int dtId  = str_value.indexOf(".");
        return str_value.subSequence(0,dtId+2).toString();
    }

}
