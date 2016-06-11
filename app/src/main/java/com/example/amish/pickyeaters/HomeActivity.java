package com.example.amish.pickyeaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.amish.pickyeaters.Restaurant;


public class HomeActivity extends AppCompatActivity {

    private YelpAPIFactory apiFactory;
    private YelpAPI yelpAPI;
    private Map<String, String> params = new HashMap<>();


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
        Response<SearchResponse> response;


        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                //JSON response
                SearchResponse searchResponse = response.body();

                ArrayList<Restaurant> tempList = new ArrayList<Restaurant>();

                for (com.yelp.clientlib.entities.Business  restaurant : searchResponse.businesses()){
                    Restaurant r = new Restaurant(restaurant.id(), restaurant.name(), restaurant.displayPhone(), restaurant.rating());
                    tempList.add(r);
                }
                Log.d("Greeting",searchResponse.businesses().toString());
                // Update UI text with the searchResponse.
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
