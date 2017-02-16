package com.weather.forecast.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class WeatherDeserializer implements JsonDeserializer<Forecast> {


    @Override
    public Forecast deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Forecast forecast = new Forecast();
        JsonObject json_object = (JsonObject) json;
        JsonObject coord = json_object.get("city").getAsJsonObject().getAsJsonObject("coord");


        forecast.setCityName(json_object.get("city").getAsJsonObject().get("name").getAsString());
        forecast.setLogitude(coord.get("lon").getAsFloat());
        forecast.setLatitude(coord.get("lat").getAsFloat());
        forecast.setCnt(json_object.get("cnt").getAsInt());
        JsonArray arr = json_object.get("list").getAsJsonArray();
        List<WeatherBy3Hour> weatherList = new ArrayList();

        int minTemp = 400;
        int maxTemp = -273;
        if(forecast.getCnt()<40){
            for (int i = 0 ; i < (40-forecast.getCnt());i++){
                weatherList.add(new WeatherBy3Hour());
            }
        }

        for(int i=0;i < forecast.getCnt();i++){

            JsonObject object = arr.get(i).getAsJsonObject();
            JsonObject main     = object.get("main").getAsJsonObject();
            JsonObject weather_object  = object.get("weather").getAsJsonArray().get(0).getAsJsonObject();
            String     dt_txt   = object.get("dt_txt").getAsString();


            WeatherBy3Hour weather = new WeatherBy3Hour();
            weather.setTemp(main.get("temp").getAsInt()-273);
            weather.setPressure(main.get("pressure").getAsFloat());
            weather.setHumidity(main.get("humidity").getAsInt());
            weather.setWeather_description(weather_object.get("description").getAsString());
            weather.setWeatherIcon(weather_object.get("id").getAsInt());
            weather.setDt_txt(dt_txt);

            weatherList.add(weather);

            if(minTemp > main.get("temp").getAsInt())
                minTemp = main.get("temp").getAsInt();
            if(maxTemp < main.get("temp").getAsInt())
                maxTemp = main.get("temp").getAsInt();


            if((i+40-forecast.getCnt()+1) % 8 == 0){
                WeatherByDay weatherByDay = new WeatherByDay();
                weatherByDay.setWeather(weatherList);
                weatherByDay.setMaxTemp(maxTemp-273);
                weatherByDay.setMinTemp(minTemp-273);
                weatherByDay.setDayInfo(weather.getDt_txt());
                forecast.addWeatherByDay(weatherByDay);

                weatherList = new ArrayList();
            }

        }
        forecast.getCityName();

        return forecast;
    }
}
