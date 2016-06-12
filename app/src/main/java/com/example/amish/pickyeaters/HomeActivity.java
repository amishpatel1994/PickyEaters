package com.example.amish.pickyeaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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

import com.example.amish.pickyeaters.Restaurant;


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

        final TextView txt = (TextView) findViewById(R.id.testView);
        txt.setText("BOO!");
        // general params
        params.put("term", "food");
        params.put("limit", "10");
        params.put("sort", "2");

        // locale params
        params.put("lang", "en");

        //TODO: Test out this amazingness and make a list view!!!
        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RestaurantsAdapter(restaurantList);
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
                    Restaurant r = new Restaurant(restaurant.id(), restaurant.name(), restaurant.displayPhone(), restaurant.rating());
                    restaurantList.add(r);
                }
                mAdapter.notifyDataSetChanged();
                txt.setText("Success");
                Log.d("Greeting",searchResponse.businesses().toString());
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                txt.setText("Failure");
                Log.d("Failed response", call.toString());
            }
        };

        call.enqueue(callback);

    }
}
