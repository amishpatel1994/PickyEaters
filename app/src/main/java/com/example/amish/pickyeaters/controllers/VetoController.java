package com.example.amish.pickyeaters.controllers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.helpers.RestaurantsAdapter;
import com.example.amish.pickyeaters.helpers.YelpAPIService;
import com.example.amish.pickyeaters.models.VetoModel;
import com.example.amish.pickyeaters.views.VetoView;

import java.util.ArrayList;

/**
 * Created by amish on 24/06/16.
 */
public class VetoController {

    private application mApplication;
    private VetoView view;
    private VetoModel model;

    public VetoController() {
        mApplication = application.getInstance();

    }

    public void init(VetoModel mod, VetoView v) {
        this.view = v;
        this.model = mod;
        initRestaurantAdapter();
        getRestaurantsFromYelp();
    }

    public void initRestaurantAdapter() {
        final RestaurantsAdapter mAdapter = new RestaurantsAdapter(mApplication.getRestaurants(), view);
        view.initRecyclerViewWithAdapter(mAdapter);
        model.setRestaurantsAdapter(mAdapter);
    }

    public void getRestaurantsFromYelp() {
        Double[] latAndLng = getLaAndLng();
        YelpAPIService yelpApi = new YelpAPIService(view.getString(R.string.yelp_consumer_key), view.getString(R.string.yelp_consumer_secret),
                view.getString(R.string.yelp_token), view.getString(R.string.yelp_token_secret),10) {
            @Override public void onPostExecute(ArrayList<Restaurant> result)
            {
                mApplication.restaurants.addAll(result);
                model.getRestaurantsAdapter().notifyDataSetChanged();
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
