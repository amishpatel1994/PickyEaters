package com.example.amish.pickyeaters.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.models.HomeModel;

/**
 * Created by Akshat on 2016-06-22.
 */
public class HomeView extends AppCompatActivity {

    private application mApplication;
    private Button btnNew;
    private Button btnJoin;
    private EditText sessionIDEditText;
    private HomeModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);
        mApplication = (application)getApplication();
        mApplication.setHomeView(this);
        mApplication.initHomeController();
        model = mApplication.getHomeModel();

        //Initialising buttons
        btnNew = (Button) findViewById(R.id.home_new_button);
        btnJoin = (Button) findViewById(R.id.home_join_button);
        sessionIDEditText = (EditText) findViewById(R.id.home_edittext_sessionID);
        initListeners();
    }

    private void initListeners(){
        btnNew.setOnClickListener(btnNewClickListener);
        btnJoin.setOnClickListener(btnJoinClickListener);
    }

    public EditText getSessionIDEditText() {
        return sessionIDEditText;
    }

    // Update the number of users
    public void updateSessionEditText() {
        sessionIDEditText.setText(model.getSessionID());
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

