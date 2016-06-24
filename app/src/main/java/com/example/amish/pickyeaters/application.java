package com.example.amish.pickyeaters;

import android.app.Application;
import android.content.res.Configuration;

import com.example.amish.pickyeaters.controllers.HomeController;
import com.example.amish.pickyeaters.models.HomeModel;
import com.example.amish.pickyeaters.views.HomeView;

/**
 * Created by Akshat on 2016-06-22.
 */
public class application extends Application {
    private static application thisApp;
    private HomeView homeView;
    private HomeModel homeModel;
    private HomeController homeController;

    public static application getInstance(){
        return thisApp;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        thisApp = this;
        homeController = new HomeController();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void initHomeController(){
        homeController.init(getHomeModel(), getHomeView());
    }

    public HomeController getHomeController(){
        return homeController;
    }

    public HomeView getHomeView(){
        if (homeView == null){
            homeView = new HomeView();
        }
        return homeView;
    }

    public void setHomeView(HomeView view){
        this.homeView = view;
    }

    public HomeModel getHomeModel(){
        if (homeModel == null){
            homeModel = new HomeModel();
        }
        return homeModel;
    }

    public void setHomeModel(HomeModel model){
        this.homeModel = model;
    }

}
