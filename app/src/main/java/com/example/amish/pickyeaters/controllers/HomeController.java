package com.example.amish.pickyeaters.controllers;

import android.widget.Toast;

import com.example.amish.pickyeaters.models.HomeModel;
import com.example.amish.pickyeaters.views.HomeView;
import com.example.amish.pickyeaters.application;

/**
 * Created by Akshat on 2016-06-22.
 */
public class HomeController {

    application mApplication;
    private HomeModel model;
    private HomeView view;

    public HomeController(){
        mApplication = application.getInstance();
    }

    public void init(HomeModel model, HomeView view){
        this.model = model;
        this.view = view;
    }

    public void newButtonPressed(){
        if (view == null){
            mApplication.initHomeController();
        }
        Toast.makeText(view.getApplicationContext(), "new button pressed", Toast.LENGTH_SHORT).show();
    }

    //Todo, update the homeModel with the editText session id.
    public void joinButtonPressed(){
        if (view == null){
            mApplication.initHomeController();
        }
        Toast.makeText(view.getApplicationContext(), "join button pressed", Toast.LENGTH_SHORT).show();
    }
}
