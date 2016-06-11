package com.example.amish.pickyeaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private YelpAPIFactory apiFactory = new YelpAPIFactory(
            this.getString(R.string.yelp_consumer_key), this.getString(R.string.yelp_consumer_secret),
            this.getString(R.string.yelp_token), this.getString(R.string.yelp_token_secret));
    private YelpAPI yelpAPI = apiFactory.createAPI();


    private Map<String, String> params = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // general params
        params.put("term", "food");
        params.put("limit", "3");

        // locale params
        params.put("lang", "en");

        //TODO: Test out this amazingness and make a list view!!!
        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
        Response<SearchResponse> response;
        try {
            response = call.execute();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };

        call.enqueue(callback);

    }
}
