package com.example.amish.pickyeaters.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amish on 24/06/16.
 */
public class JSONParser {
    private JSONArray obj;

    public JSONParser (String param) {
        try {
            obj = new JSONArray(param);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Restaurant> getRestaurantList() throws JSONException {
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

        //JSONArray restaurants = obj.getJSONArray("restaurants");

        for (int i = 0; i < obj.length(); i++) {
            JSONObject rest = obj.getJSONObject(i);
            Restaurant restaurant = new Restaurant(rest.getString("id"),
                    rest.getString("name"), rest.getString("phone"),
                    rest.getString("address"), rest.getString("imageUrl"),
                    rest.getString("description"), rest.getDouble("rating"),
                    rest.getDouble("distance"), null);
            restaurantList.add(restaurant);
        }

        return restaurantList;
    }
}
