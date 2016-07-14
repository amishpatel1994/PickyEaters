package com.example.amish.pickyeaters.models;

import android.util.Log;

import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.RestaurantsAdapter;

/**
 * Created by amish on 24/06/16.
 */
public class VetoModel {
    private int numVotes;
    private RestaurantsAdapter restaurantsAdapter;
    private application mApplication;

    public  VetoModel() {
        mApplication = application.getInstance();
    }

    public void setRestaurantsAdapter(RestaurantsAdapter restaurantsAdapter) {
        this.restaurantsAdapter = restaurantsAdapter;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        if(mApplication.getVetoView() != null){
            Log.e("setNumVotes: ","numVotes");
            mApplication.getVetoView().updateNumVotesLeft(numVotes);
        } else {
            Log.e("vetoView doesnt exist: ","numVotes");
        }
        this.numVotes = numVotes;
    }

    public RestaurantsAdapter getRestaurantsAdapter() {
        return restaurantsAdapter;
    }
}
