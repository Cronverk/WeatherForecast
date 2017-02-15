package com.weather.forecast.design.fragments;

import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.forecast.R;
import com.weather.forecast.design.views.WeatherGraph;
import com.weather.forecast.design.views.WeatherIcon;
import com.weather.forecast.entity.Forecast;
import com.weather.forecast.entity.WeatherBy3Hour;
import com.weather.forecast.entity.WeatherByDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Владелец on 02.02.2017.
 */

public class WeatherFragment extends Fragment implements WeatherGraph.OnGraphClick{

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


    List<WeatherBy3Hour> weatherByTimeList;

    public static WeatherFragment newInstance(Forecast forecast,int position) {

        Bundle args = new Bundle();

        args.putString("city_name", forecast.getCityName());
        args.putParcelable("day_weather", forecast.getWeatherByDays().get(position));

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);

        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        weatherByTimeList = new ArrayList<>();

        View  rootView = inflater.inflate(R.layout.weather_card, container, false);
        ButterKnife.bind(this, rootView);


        WeatherGraph graph = (WeatherGraph) rootView.findViewById(R.id.weatherGraph);
        WeatherByDay weather = args.getParcelable("day_weather");

        cityView.setText(args.getString("city"));
        dateView.setText(weather.getDayInfo());

        WeatherBy3Hour weather_hour = weather.getWeather().get(0);

        graph.setOnGraphClick(this);
        graph.setPoints(weather.getPointsArray());

        icon.setText(weather_hour.getWeatherIcon());
        tempView.setText(String.format(temp_text, weather_hour.getTemp()));
        humidityView.setText(String.format(humidity_text, weather_hour.getHumidity()));
        pressureView.setText(String.format(pressure_text, weather_hour.getPressure()));
        windView.setText(String.format(wind_text, weather_hour.getWindSpeed()));


        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public void click(int position) {
        if(weatherByTimeList.size() < 8)
            position-= weatherByTimeList.size();

        //tempView.setText(String.format(temp_text, roundDouble(w_forecast.getDouble("temp")-273.15)));
        ///humidityView.setText(String.format(humidity_text, roundFloat(weatherByTimeList.get(position).getMain().getHumidity())));
        //pressureView.setText(String.format(pressure_text, roundDouble(weatherByTimeList.get(position).getMain().getPressure())));
        //windView.setText(String.format(wind_text, roundDouble(args.getDouble("wind"))));

    }
}
