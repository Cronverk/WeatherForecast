package com.weather.forecast;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;


public class Splash extends AppCompatActivity implements Observer,LocationApi.GeoSplash {
    WeatherCardAdapter adapter;

    private LocationApi locator;

    int PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestPermission();

        locator = new LocationApi((LocationManager)
                getSystemService(Context.LOCATION_SERVICE), getApplicationContext(),this);
        locator.addObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locator.addUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locator.removeUpdates();
    }

    public void requestPermission(){

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Location location = (Location)arg;
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("lat",location.getLatitude());
        intent.putExtra("lon",location.getLongitude());

        startActivity(intent);
    }

    @Override
    public void requestGeoEnable() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
}