package com.example.amish.pickyeaters.models;

import com.example.amish.pickyeaters.helpers.RestaurantsAdapter;

/**
 * Created by amish on 24/06/16.
 */
public class VetoModel {
    private boolean vetoing;
    private int numVotes;
    private RestaurantsAdapter restaurantsAdapter;

    public  VetoModel() {
        vetoing = false;
    }

    public boolean isVetoing() {
        return vetoing;
    }

    public void setVetoing(boolean val) {
        vetoing = val;
    }

    public void setRestaurantsAdapter(RestaurantsAdapter restaurantsAdapter) {
        this.restaurantsAdapter = restaurantsAdapter;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public RestaurantsAdapter getRestaurantsAdapter() {
        return restaurantsAdapter;
    }
}
