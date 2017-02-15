package com.weather.forecast;

import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weather.forecast.entity.Forecast;
import com.weather.forecast.entity.WeatherDeserializer;
import com.weather.forecast.retrofit.OpenWeatherService;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherApi extends Observable implements Callback<Forecast>, Observer {

    private OpenWeatherService weatherService;
    private WeatherApp weatherApp;

    public WeatherApi(WeatherApp weatherApp){
        weatherService = weatherApp.getClient().create(OpenWeatherService.class);
        this.weatherApp = weatherApp;
    }

    public void requestWeather(double latitude, double longitude){

        Call<Forecast> call = weatherService.getWeatherForecast(latitude, longitude, weatherApp.API_KEY);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Forecast> call, Response<Forecast> response) {

        Forecast forecast = response.body();
        setChanged();
        notifyObservers(forecast);
    }

    @Override
    public void onFailure(Call<Forecast> call, Throwable t) {

    }

    @Override
    public void update(Observable o, Object arg) {
        Location location = (Location)arg;
        requestWeather(location.getLatitude(),location.getLongitude());
    }
}
