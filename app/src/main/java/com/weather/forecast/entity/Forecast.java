package com.weather.forecast.entity;

import java.util.ArrayList;
import java.util.List;

public class Forecast{

    private String cityName;
    private float logitude;
    private float latitude;
    private Integer cnt;

    public Forecast() {
        weatherByDays = new ArrayList<>();
    }

    private List<WeatherByDay> weatherByDays = null;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<WeatherByDay> getWeatherByDays() {
        return weatherByDays;
    }

    public void setWeatherByDays(List<WeatherByDay> weatherByDays) {
        this.weatherByDays = weatherByDays;
    }

    public void addWeatherByDay(WeatherByDay weatherByDay){
        this.weatherByDays.add(weatherByDay);
    }

    public float getLogitude() {
        return logitude;
    }

    public void setLogitude(float logitude) {
        this.logitude = logitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
