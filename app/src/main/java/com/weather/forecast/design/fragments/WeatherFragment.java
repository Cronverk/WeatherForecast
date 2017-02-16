package com.weather.forecast.design.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherFragment extends Fragment implements WeatherGraph.OnGraphClick{

    @BindView(R.id.weather_icon)WeatherIcon icon;
    @BindView(R.id.date)        TextView dateView;
    @BindView(R.id.city)        TextView cityView;
    @BindView(R.id.temperature) TextView tempView;
    @BindView(R.id.humidity_value)    TextView humidityView;
    @BindView(R.id.pressure_value)    TextView pressureView;
    @BindView(R.id.wind_value)        TextView windView;

    @BindString(R.string.humidity_text) String humidity_text;
    @BindString(R.string.pressure_text) String pressure_text;
    @BindString(R.string.temperature_text) String temp_text;
    @BindString(R.string.wind_text)     String wind_text;


    List<WeatherBy3Hour> weatherByTimeList;

    public static WeatherFragment newInstance(Forecast forecast,int position) {

        Bundle args = new Bundle();

        args.putString("city_name", forecast.getCityName());
        args.putParcelable("day_weather", forecast.getWeatherByDays().get(position));
        if(position == 0 )
            args.putInt("position",40 - forecast.getCnt());
        else args.putInt("position",0);

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
        weatherByTimeList = weather.getWeather();

        cityView.setText(args.getString("city_name"));
        dateView.setText(weather.getDayInfo());

        WeatherBy3Hour weather_hour = weather.getWeather().get(args.getInt("position"));

        graph.setOnGraphClick(this);
        graph.setPoints(weather.getPointsArray());

        icon.setText(weather_hour.getWeatherIcon());
        tempView.setText(String.format(temp_text, weather_hour.getTemp()));
        humidityView.setText(""+weather_hour.getHumidity());
        pressureView.setText((""+ weather_hour.getPressure()));
        windView.setText(""+ weather_hour.getWindSpeed());


        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public void click(int position) {

        WeatherBy3Hour w3h = weatherByTimeList.get(position);
        tempView.setText(String.format(temp_text, w3h.getTemp()));
        humidityView.setText(""+w3h.getHumidity());
        pressureView.setText(""+w3h.getPressure());
        windView.setText(""+w3h.getWindSpeed());

    }
}
