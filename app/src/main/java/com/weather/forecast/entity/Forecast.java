package com.weather.forecast.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast{

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<WeatherByTime> weatherByTime = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<WeatherByTime> getWeatherByTimeList() {
        return weatherByTime;
    }

    public void setWeatherByTimeList(List<WeatherByTime> weatherByTime) {
        this.weatherByTime = weatherByTime;
    }
}
