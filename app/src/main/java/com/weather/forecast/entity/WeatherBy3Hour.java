package com.weather.forecast.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WeatherBy3Hour implements Parcelable {

    int     temp;
    float   pressure;
    int     humidity;

    String  weather_description;
    int  weather_icon;

    float   wind_speed;
    String  dt_txt;

    public WeatherBy3Hour() {
        temp = -273;
        pressure = 0f;
        humidity = 0;
        weather_description = "";
        weather_icon = 0;
        wind_speed = 0;
        dt_txt = "";
    }

    protected WeatherBy3Hour(Parcel in) {
        temp = in.readInt();
        pressure = in.readFloat();
        humidity = in.readInt();
        weather_description = in.readString();
        weather_icon = in.readInt();
        wind_speed = in.readFloat();
        dt_txt = in.readString();
    }

    public static final Creator<WeatherBy3Hour> CREATOR = new Creator<WeatherBy3Hour>() {
        @Override
        public WeatherBy3Hour createFromParcel(Parcel in) {
            return new WeatherBy3Hour(in);
        }

        @Override
        public WeatherBy3Hour[] newArray(int size) {
            return new WeatherBy3Hour[size];
        }
    };

    public float getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public String getWeatherIcon() {
        return weatherIcons.get(weather_icon);
    }

    public void setWeatherIcon(int weather_icon) {
        this.weather_icon = weather_icon;
    }

    public float getWindSpeed() {
        return wind_speed;
    }

    public void setWindSpeed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(temp);
        dest.writeFloat(pressure);
        dest.writeInt(humidity);
        dest.writeString(weather_description);
        dest.writeInt(weather_icon);
        dest.writeFloat(wind_speed);
        dest.writeString(dt_txt);
    }

    private static final Map<Integer, String> weatherIcons = Collections.unmodifiableMap(
            new HashMap<Integer, String>() {{
                put(200,"\uf01d");
                put(201,"\uf01d");
                put(202,"\uf01d");
                put(210,"\uf01d");
                put(230,"\uf01d");
                put(231,"\uf01d");
                put(232,"\uf01d");
                put(211,"\uf01e");
                put(212,"\uf01e");
                put(221,"\uf01e");
                put(300,"\uf01c");
                put(301,"\uf01c");
                put(302,"\uf01c");
                put(310,"\uf01c");
                put(311,"\uf01c");
                put(312,"\uf01c");
                put(313,"\uf01c");
                put(314,"\uf01c");
                put(321,"\uf01c");
                put(500,"\uf01b");
                put(501,"\uf019");
                put(502,"\uf019");
                put(503,"\uf019");
                put(504,"\uf019");
                put(511,"\uf017");
                put(615,"\uf017");
                put(616,"\uf017");
                put(620,"\uf017");
                put(621,"\uf017");
                put(622,"\uf017");
                put(520,"\uf01a");
                put(521,"\uf01a");
                put(522,"\uf01a");
                put(531,"\uf01a");
                put(600,"\uf01b");
                put(601,"\uf01b");
                put(602,"\uf01b");
                put(611,"\uf0b5");
                put(612,"\uf0b5");
                put(701,"\uf01c");
                put(711,"\uf062");
                put(721,"\uf0b6");
                put(731,"\uf011");
                put(741,"\uf014");
                put(751,"\uf011");
                put(761,"\uf063");
                put(762,"\uf074");
                put(771,"\uf085");
                put(781,"\uf056");
                put(900,"\uf056");
                put(800,"\uf00d");
                put(951,"\uf00d");
                put(801,"\uf013");
                put(802,"\uf013");
                put(803,"\uf013");
                put(804,"\uf013");
                put(901,"\uf073");
                put(902,"\uf073");
                put(903,"\uf076");
                put(904,"\uf072");
                put(905,"\uf021");
                put(906,"\uf015");
                put(952,"\uf011");
                put(953,"\uf011");
                put(954,"\uf011");
                put(955,"\uf011");
                put(956,"\uf011");
                put(957,"\uf011");
                put(958,"\uf011");
                put(959,"\uf011");
                put(962,"\uf011");
                put(960,"\uf01e");
                put(961,"\uf01e");
            }});
}

