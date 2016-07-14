package com.example.amish.pickyeaters.views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.helpers.RestaurantsAdapter;


public class VetoView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private application mApplication;
    private ProgressBar vetoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//Add this to your Recyclerview
        setContentView(R.layout.veto_view);
        mApplication = (application)getApplication();
        mApplication.setVetoView(this);
        mApplication.initVetoController();

        vetoProgressBar = (ProgressBar) findViewById(R.id.veto_progressBar);
        vetoProgressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void removeVetoProgressBar() {
        vetoProgressBar.setVisibility(View.INVISIBLE);
    }

    public void initRecyclerViewWithAdapter(RestaurantsAdapter adapter) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //recyclerView.findViewHolderForLayoutPosition()

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mStartActivity = new Intent(getApplicationContext(), HomeView.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
