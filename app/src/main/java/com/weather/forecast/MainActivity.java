package com.weather.forecast;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weather.forecast.entity.Forecast;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    WeatherCardAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        adapter = new WeatherCardAdapter(getSupportFragmentManager(), new Forecast());
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        Bundle b = getIntent().getExtras();


        WeatherApp app = ((WeatherApp) getApplicationContext());
        WeatherApi weatherService = new WeatherApi(app,b.getDouble("lat"),b.getDouble("lon"));
        weatherService.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        adapter.updateForecasts((Forecast) arg);
        adapter.notifyDataSetChanged();
    }
}
