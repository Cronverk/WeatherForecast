package com.weather.forecast;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Observable;


public class LocationApi extends Observable implements LocationListener{
    LocationManager locationManager;
    Location location;
    Context context;
    String provider;
    GeoSplash geoSplash;

    public LocationApi(LocationManager locationManager, Context context,GeoSplash geoSplash) {
        this.locationManager = locationManager;
        this.context = context;
        this.geoSplash = geoSplash;
        provider = locationManager.getBestProvider(new Criteria(), false);

    }

    @Override
    public void onLocationChanged(Location location) {
        if(this.location == null){
            this.location = location;
            setChanged();
            notifyObservers(location);
        }

        if (distance(this.location,location) > 1) { // if distance < 0.1 miles we take locations as equal
            this.location = location;
            setChanged();
            notifyObservers(location);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void removeUpdates(){
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
    }

    public void addUpdates(){
        requestGeo();
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    public void requestGeo() {
        boolean enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            geoSplash.requestGeoEnable();
        }
    }



    /** calculates the distance between two locations in MILES */
    private double distance(Location loc1, Location loc2) {

        double earthRadius = 6371; //   kilometer output

        double dLat = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double dLng = Math.toRadians(loc2.getLongitude()- loc1.getLongitude());

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist; // output distance, in KM
    }

    interface GeoSplash{

        void requestGeoEnable();
    }
}
