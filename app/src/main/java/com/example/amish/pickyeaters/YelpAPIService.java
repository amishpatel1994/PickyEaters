package com.example.amish.pickyeaters;

import android.os.AsyncTask;
import android.util.Log;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by amish on 22/06/16.
 */
public class YelpAPIService extends AsyncTask<Double, Void, ArrayList<Restaurant>> {
    private YelpAPIFactory apiFactory;
    private YelpAPI yelpAPI;
    private Map<String, String> params = new HashMap<>();
    private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

    public YelpAPIService(String consumerKey, String consumerSecret, String token, String tokenSecret, int limit) {
        apiFactory = new YelpAPIFactory(consumerKey,consumerSecret,token,tokenSecret);
        yelpAPI = apiFactory.createAPI();

        // general params
        params.put("term", "food");
        params.put("limit", String.valueOf(limit));
        params.put("sort", "2");

        // locale params
        params.put("lang", "en");
    }

    protected void onPreExecute (){
        Log.d("PreExceute","On pre Exceute......");
    }

    @Override
    protected ArrayList<Restaurant> doInBackground (Double ...parms) {
        Double[] latAndLng = parms.clone();
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latAndLng[0])
                .longitude(latAndLng[1]).build();
        Call<SearchResponse> call = yelpAPI.search(coordinate, params);

        try {
            Response<SearchResponse> response = call.execute();

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

            return restaurantList;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
