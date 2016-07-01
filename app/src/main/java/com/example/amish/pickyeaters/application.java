package com.example.amish.pickyeaters;

import android.app.Application;
import android.content.res.Configuration;

import com.example.amish.pickyeaters.controllers.CaptainController;
import com.example.amish.pickyeaters.controllers.HomeController;
import com.example.amish.pickyeaters.controllers.VetoController;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.models.CaptainModel;
import com.example.amish.pickyeaters.models.HomeModel;
import com.example.amish.pickyeaters.models.VetoModel;
import com.example.amish.pickyeaters.views.CaptainView;
import com.example.amish.pickyeaters.views.HomeView;
import com.example.amish.pickyeaters.views.VetoView;

import java.util.ArrayList;

/**
 * Created by Akshat on 2016-06-22.
 */
public class application extends Application {
    private static application thisApp;
    private HomeView homeView;
    private HomeModel homeModel;
    private HomeController homeController;
    private VetoController vetoController;
    private VetoModel vetoModel;
    private VetoView vetoView;
    private CaptainModel captainModel;
    private CaptainView captainView;
    private CaptainController captainController;
    public ArrayList<Restaurant> restaurants;

    public static application getInstance(){
        return thisApp;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        thisApp = this;
        homeController = new HomeController();
        restaurants = new ArrayList<Restaurant>();
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

    //
    public void initVetoController() {
        if (vetoController == null) {
            vetoController = new VetoController();
        }
        vetoController.init(getVetoModel(),getVetoView());
    }

    public VetoModel getVetoModel() {
        if (vetoModel == null)
            vetoModel = new VetoModel();
        return vetoModel;
    }

    public VetoView getVetoView() {
        int i = 0;
        if (vetoView == null)
            vetoView = new VetoView();
        return vetoView;
    }

    public void setVetoController(VetoController vetoController) {
        this.vetoController = vetoController;
    }

    public void setVetoModel(VetoModel vetoModel) {
        this.vetoModel = vetoModel;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setVetoView(VetoView vetoView) {
        this.vetoView = vetoView;
    }

    public void setCaptainView(CaptainView captainView) {
        this.captainView = captainView;
    }



    public void initCaptainController() {
        if (captainController == null) {
            captainController = new CaptainController();
        }
        captainController.init(getCaptainModel(),getCaptainView());
    }

    public void setCaptainModel(CaptainModel captainModel) {
        this.captainModel = captainModel;
    }

    public CaptainModel getCaptainModel() {
        if (captainModel == null)
            captainModel = new CaptainModel();
        return captainModel;
    }

    public CaptainView getCaptainView() {
        if (captainView == null)
            captainView = new CaptainView();
        return captainView;
    }

    public CaptainController getCaptainController() {
        return captainController;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
