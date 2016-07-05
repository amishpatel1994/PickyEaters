package com.example.amish.pickyeaters.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.example.amish.pickyeaters.helpers.ServerWrapper;
import com.example.amish.pickyeaters.models.HomeModel;
import com.example.amish.pickyeaters.views.CaptainView;
import com.example.amish.pickyeaters.views.HomeView;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.views.VetoView;
import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by Akshat on 2016-06-22.
 */
public class HomeController {

    private application mApplication;
    private HomeModel model;
    private HomeView view;

    public HomeController(){
        mApplication = application.getInstance();
    }

    public void init(HomeModel model, HomeView view){
        this.model = model;
        this.view = view;
        initServerConnection();
    }

    public void newButtonPressed(){
        if (view == null){
            mApplication.initHomeController();
        }


        Intent captainIntent = new Intent(view.getApplicationContext(), CaptainView.class);
        view.startActivity(captainIntent);

        Toast.makeText(view.getApplicationContext(), "new button pressed", Toast.LENGTH_SHORT).show();
    }

    // Creates a ServerWrapper instance and calls it's init method to create a socket connection
    private void initServerConnection() {
        ServerWrapper sw = new ServerWrapper("http://10.0.2.2:3000");

        // Initialize socket connection and set it in application to be used by all the controllers
        Socket mSocket = sw.initConnection();
        mApplication.setmSocket(mSocket);
    }


    //Todo, update the homeModel with the editText session id.
    public void joinButtonPressed(){
        if (view == null){
            mApplication.initHomeController();
        }
        Intent vetoIntent = new Intent(view.getApplicationContext(), VetoView.class);
        view.startActivity(vetoIntent);
//        mApplication.initVetoController();
        Toast.makeText(view.getApplicationContext(), "join button pressed", Toast.LENGTH_SHORT).show();
    }
}
