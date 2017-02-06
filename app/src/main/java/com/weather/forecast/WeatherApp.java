package com.weather.forecast;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Владелец on 20.01.2017.
 */

public class WeatherApp extends Application {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String API_KEY = "eaed25360ad1f75f4cd0e838f7dc0820";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
