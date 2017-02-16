package com.weather.forecast;

import com.weather.forecast.entity.Forecast;
import com.weather.forecast.retrofit.OpenWeatherService;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherApi extends Observable implements Callback<Forecast> {

    private OpenWeatherService weatherService;
    private WeatherApp weatherApp;

    public WeatherApi(WeatherApp weatherApp,double lat,double lon){
        weatherService = weatherApp.getClient().create(OpenWeatherService.class);
        this.weatherApp = weatherApp;

        requestWeather(lat,lon);
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
}
