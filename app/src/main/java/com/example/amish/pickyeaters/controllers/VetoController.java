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
import com.example.amish.pickyeaters.helpers.ServerSender;
import com.example.amish.pickyeaters.helpers.YelpAPIService;
import com.example.amish.pickyeaters.models.VetoModel;
import com.example.amish.pickyeaters.views.VetoView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amish on 24/06/16.
 */
public class VetoController {

    private application mApplication;
    private VetoView view;
    private VetoModel model;
    private ServerSender serverSender;

    public VetoController() {
        mApplication = application.getInstance();
    }

    public void init(VetoModel mod, VetoView v) {
        this.view = v;
        this.model = mod;
        serverSender = new ServerSender();
        initRestaurantAdapter();
    }

    public void initRestaurantAdapter() {
        final RestaurantsAdapter mAdapter = new RestaurantsAdapter(mApplication.getRestaurants(), view);
        view.initRecyclerViewWithAdapter(mAdapter);
        model.setRestaurantsAdapter(mAdapter);
    }

}
