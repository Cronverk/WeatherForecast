package com.weather.forecast.entity;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class WeatherByDay implements Parcelable {
    String dayInfo;
    int maxTemp;
    int minTemp;
    List<WeatherBy3Hour> weather;

    private static SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("E d", Locale.ENGLISH);
    }

    protected WeatherByDay(){
        dayInfo = "";
        maxTemp = -273;
        minTemp = 400;
        weather = new ArrayList<>();
    }

    protected WeatherByDay(Parcel in) {
        dayInfo = in.readString();
        maxTemp = in.readInt();
        minTemp = in.readInt();
        weather = in.createTypedArrayList(WeatherBy3Hour.CREATOR);
    }

    public static final Creator<WeatherByDay> CREATOR = new Creator<WeatherByDay>() {
        @Override
        public WeatherByDay createFromParcel(Parcel in) {
            return new WeatherByDay(in);
        }

        @Override
        public WeatherByDay[] newArray(int size) {
            return new WeatherByDay[size];
        }
    };

    public String getDayInfo() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dayInfo);
        } catch (ParseException e) {e.printStackTrace();}

        return dateFormat.format(date);
    }

    public void setDayInfo(String dayInfo) {
        this.dayInfo = dayInfo;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public List<WeatherBy3Hour> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherBy3Hour> weather) {
        this.weather = weather;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayInfo);
        dest.writeInt(maxTemp);
        dest.writeInt(minTemp);
        dest.writeTypedList(weather);
    }

    public ArrayList<PointF> getPointsArray(){
        ArrayList<PointF> points  = new ArrayList<>();
        for(int i=0 ; i < weather.size();i++){
            points.add(new PointF(i,weather.get(i).getTemp()));
        }
        return points;
    }
}
