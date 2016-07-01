package com.example.amish.pickyeaters.views;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.JSONParser;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.helpers.RestaurantsAdapter;
import com.example.amish.pickyeaters.helpers.YelpAPIService;

import java.util.ArrayList;


public class VetoView extends AppCompatActivity {
    private RecyclerView recyclerView;
    application mApplication;
    private ProgressBar vetoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veto_view);
        mApplication = (application)getApplication();
        mApplication.setVetoView(this);
        mApplication.initVetoController();

        final TextView txt = (TextView) findViewById(R.id.topLabel1);
        vetoProgressBar = (ProgressBar) findViewById(R.id.veto_progressBar);
        vetoProgressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void removeVetoProgressBar() {
        vetoProgressBar.setVisibility(View.GONE);
    }

    public void initRecyclerViewWithAdapter(RestaurantsAdapter adapter) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
