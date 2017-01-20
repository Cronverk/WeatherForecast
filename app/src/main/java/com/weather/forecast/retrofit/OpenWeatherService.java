package com.weather.forecast.retrofit;


import com.weather.forecast.entity.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Владелец on 20.01.2017.
 */

public interface OpenWeatherService{
    @GET("data/2.5/forecast")
    Call<Forecast> getWeatherForecast(@Query("lat") double lat,
                                      @Query("lon") double lon,
                                      @Query("appid") String API_KEY);

}
