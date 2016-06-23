package com.example.amish.pickyeaters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

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

        //Initialising buttons
        btnNew = (Button) findViewById(R.id.home_new_button);
        btnJoin = (Button) findViewById(R.id.home_join_button);
    }
}

