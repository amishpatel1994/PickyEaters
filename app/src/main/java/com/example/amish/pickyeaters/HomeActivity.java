package com.example.amish.pickyeaters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    private RecyclerView recyclerView;
    private RestaurantsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView txt = (TextView) findViewById(R.id.topLabel1);

        // Defaults to latitude and longitude of San Francisco
        double lat = 37.7749;
        double lng = -122.4194;

        // Get the latitude and longitude based on android
        LocationManager lManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean netEnabled = lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (netEnabled) {
            // checks if we have the required permissions. If we don't, then request them
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RestaurantsAdapter(restaurantList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        YelpAPIService yelpApi = new YelpAPIService(this.getString(R.string.yelp_consumer_key), this.getString(R.string.yelp_consumer_secret),
                this.getString(R.string.yelp_token), this.getString(R.string.yelp_token_secret),10) {
            @Override public void onPostExecute(ArrayList<Restaurant> result)
            {
                restaurantList.addAll(result);
                mAdapter.notifyDataSetChanged();
            }
        };

        yelpApi.execute(lat,lng);
    }
}
