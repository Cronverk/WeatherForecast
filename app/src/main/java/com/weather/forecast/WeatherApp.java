package com.weather.forecast;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weather.forecast.entity.Forecast;
import com.weather.forecast.entity.WeatherDeserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherApp extends Application {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String API_KEY = "eaed25360ad1f75f4cd0e838f7dc0820";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Forecast.class, new WeatherDeserializer())
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
