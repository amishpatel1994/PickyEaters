package com.example.amish.pickyeaters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.yelp.clientlib.entities.options.CoordinateOptions;


public class HomeActivity extends AppCompatActivity {

    private YelpAPIFactory apiFactory;
    private YelpAPI yelpAPI;
    private Map<String, String> params = new HashMap<>();
    private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    private RecyclerView recyclerView;
    private RestaurantsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        apiFactory = new YelpAPIFactory(
                this.getString(R.string.yelp_consumer_key), this.getString(R.string.yelp_consumer_secret),
                this.getString(R.string.yelp_token), this.getString(R.string.yelp_token_secret));
        yelpAPI = apiFactory.createAPI();

        final TextView txt = (TextView) findViewById(R.id.topLabel1);
        // general params
        params.put("term", "food");
        params.put("limit", "10");
        params.put("sort", "2");

        // locale params
        params.put("lang", "en");

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
        //TODO: Test out this amazingness and make a list view!!!
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(lat)
                .longitude(lng).build();
        Call<SearchResponse> call = yelpAPI.search(coordinate, params);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RestaurantsAdapter(restaurantList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);




        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                //JSON response
                SearchResponse searchResponse = response.body();

                for (com.yelp.clientlib.entities.Business  restaurant : searchResponse.businesses()){
                    String addr = "";
                    ArrayList<String> addrList = restaurant.location().displayAddress();
                    for (int i = 0; i < addrList.size(); i++) {
                        addr += addrList.get(i);
                        if (i < addrList.size()-1) {
                            addr += ", ";
                        }
                    }

                    restaurant.location().displayAddress();
                    Log.d("THE RESTAURANT", restaurant.location().toString());
                    Restaurant r = new Restaurant(restaurant.id(), restaurant.name(), restaurant.displayPhone(), addr, restaurant.rating());
                    restaurantList.add(r);
                }
                mAdapter.notifyDataSetChanged();
                Log.d("Greeting",searchResponse.businesses().toString());
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                Log.d("Failed response", call.toString());
            }
        };

        call.enqueue(callback);

    }
}
