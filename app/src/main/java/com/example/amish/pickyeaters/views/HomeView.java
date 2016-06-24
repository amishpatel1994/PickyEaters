package com.example.amish.pickyeaters.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;

/**
 * Created by Akshat on 2016-06-22.
 */
public class HomeView extends AppCompatActivity {

    application mApplication;
    Button btnNew;
    Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);
        mApplication = (application)getApplication();
        mApplication.setHomeView(this);

        //Initialising buttons
        btnNew = (Button) findViewById(R.id.home_new_button);
        btnJoin = (Button) findViewById(R.id.home_join_button);

        initListeners();
    }

    private void initListeners(){
        btnNew.setOnClickListener(btnNewClickListener);
        btnJoin.setOnClickListener(btnJoinClickListener);
    }

    View.OnClickListener btnNewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mApplication.getHomeController().newButtonPressed();
        }
    };

    View.OnClickListener btnJoinClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mApplication.getHomeController().joinButtonPressed();
}
};

}

