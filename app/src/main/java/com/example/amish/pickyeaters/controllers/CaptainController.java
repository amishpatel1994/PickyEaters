package com.example.amish.pickyeaters.controllers;

import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.models.CaptainModel;
import com.example.amish.pickyeaters.views.CaptainView;

/**
 * Created by amish on 30/06/16.
 */
public class CaptainController {
    private application mApplication;
    private CaptainModel model;
    private CaptainView view;

    public CaptainController(){
        mApplication = application.getInstance();
    }

    public void init(CaptainModel model, CaptainView view){
        this.model = model;
        this.view = view;
    }

    public void startVetoProcess() {

    }
}