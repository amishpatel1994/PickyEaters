package com.example.amish.pickyeaters.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.helpers.ServerSender;
import com.example.amish.pickyeaters.helpers.YelpAPIService;
import com.example.amish.pickyeaters.models.CaptainModel;
import com.example.amish.pickyeaters.views.CaptainView;
import com.example.amish.pickyeaters.views.VetoView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amish on 30/06/16.
 */
public class CaptainController {
    private application mApplication;
    private CaptainModel model;
    private CaptainView view;
    private ServerSender serverSender;

    public CaptainController(){
        mApplication = application.getInstance();
    }

    public void init(CaptainModel model, CaptainView view){
        this.model = model;
        this.view = view;
        serverSender = new ServerSender();
    }

    public void startVetoProcess(String distance) {
        Intent vetoIntent = new Intent(view.getApplicationContext(), VetoView.class);
        if(distance.isEmpty() || Integer.parseInt(distance) > 40 ){
            mApplication.setDistanceSetting("40");
        }
        else {
            mApplication.setDistanceSetting(distance);
        }
        view.startActivity(vetoIntent);
    }

    public void sessionIdUpdated(final String sessID){
        view.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                view.updateSessionID(sessID);
            }
        });
    }

    // Update the number of users when server replies with a "joined" event meaning
    // another user joined the session
    public void updateNumberOfUsers(final String numUsers) {
        view.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                view.updateNumUsersField(numUsers);
            }
        });

    }
}
