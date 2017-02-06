package com.weather.forecast;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weather.forecast.entity.Forecast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    WeatherCardAdapter adapter;

    private LocationApi locator;

    int PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        adapter = new WeatherCardAdapter(getSupportFragmentManager(), new ArrayList<Forecast>());
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        locator = new LocationApi((LocationManager)
                getSystemService(Context.LOCATION_SERVICE), getApplicationContext());


        WeatherApp app = ((WeatherApp) getApplicationContext());
        WeatherApi weatherService = new WeatherApi(app);
        weatherService.addObserver(this);
        locator.addObserver(weatherService);
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
        adapter.updateForecasts((ArrayList<Forecast>) arg);
        adapter.notifyDataSetChanged();
    }
}
