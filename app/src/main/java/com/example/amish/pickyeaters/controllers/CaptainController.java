package com.example.amish.pickyeaters.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.helpers.ServerSender;
import com.example.amish.pickyeaters.helpers.YelpAPIService;
import com.example.amish.pickyeaters.models.CaptainModel;
import com.example.amish.pickyeaters.views.CaptainView;
import com.example.amish.pickyeaters.views.VetoView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amish on 30/06/16.
 */
public class CaptainController {
    private application mApplication;
    private CaptainModel model;
    private CaptainView view;
    private ServerSender serverSender;
    private ArrayList<JSONObject> jsonArray;

    public CaptainController(){
        mApplication = application.getInstance();
    }

    public void init(CaptainModel model, CaptainView view){
        this.model = model;
        this.view = view;
        serverSender = new ServerSender();
        getRestaurantsFromYelp();
    }

    public void startVetoProcess(String distance) {
        Intent vetoIntent = new Intent(view.getApplicationContext(), VetoView.class);
        mApplication.setDistanceSetting(distance);
        view.startActivity(vetoIntent);
    }

    public void getRestaurantsFromYelp() {
        Double[] latAndLng = getLaAndLng();
        YelpAPIService yelpApi = new YelpAPIService(view.getString(R.string.yelp_consumer_key), view.getString(R.string.yelp_consumer_secret),
                view.getString(R.string.yelp_token), view.getString(R.string.yelp_token_secret),10, mApplication.getDistanceSetting()) {
            @Override public void onPostExecute(ArrayList<Restaurant> result)
            {
                mApplication.restaurants.addAll(result);

                //Make an array JSON objects out of all restaurants and send to server
                jsonArray = new ArrayList<JSONObject>();
                for (Restaurant rest : mApplication.restaurants){
                    jsonArray.add(rest.getJSON());
                }
                serverSender.sendMessage(jsonArray);
                //mApplication.getVetoModel().getRestaurantsAdapter().notifyDataSetChanged();
                //mApplication.getVetoView().removeVetoProgressBar();
            }
        };

        yelpApi.execute(latAndLng[0],latAndLng[1]);
    }

    public Double[] getLaAndLng () {
        // Defaults to latitude and longitude of San Francisco
        double lat = 37.7749;
        double lng = -122.4194;

        // Get the latitude and longitude based on android
        LocationManager lManager = (LocationManager)view.getSystemService(Context.LOCATION_SERVICE);
        boolean netEnabled = lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (netEnabled) {
            // checks if we have the required permissions. If we don't, then request them
            if (ContextCompat.checkSelfPermission(view, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(view, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(view,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            }
            // Get the location (lat, lng)
            Location location = lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null)
            {
                lat = location.getLatitude();
                lng = location.getLongitude();
            }

            Log.d("location latitude", Double.toString(lat));
            Log.d("location longitude", Double.toString(lng));

        }
        Double[] latLng = {lat,lng};
        return latLng;
    }
}
