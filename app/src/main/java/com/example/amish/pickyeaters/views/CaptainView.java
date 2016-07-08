package com.example.amish.pickyeaters.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.controllers.CaptainController;
import com.example.amish.pickyeaters.helpers.Restaurant;
import com.example.amish.pickyeaters.models.CaptainModel;

import java.util.ArrayList;

/**
 * Created by Akshat on 2016-06-24.
 */
public class CaptainView extends AppCompatActivity {

    private application mApplication;
    private Button btnStart;
    private TextView sessionID;
    private EditText distanceText;
    private TextView userCount;
    private CaptainModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_view);
        mApplication = (application)getApplication();
        mApplication.setCaptainView(this);
        mApplication.initCaptainController();
        model = mApplication.getCaptainModel();

        //Initialising View elements
        btnStart = (Button) findViewById(R.id.captain_startveto_button);
        sessionID = (TextView) findViewById(R.id.captain_session_id);
        distanceText = (EditText) findViewById(R.id.captain_distanceEditText);
        userCount = (TextView) findViewById(R.id.captain_userCount);

        if(model.getSessID() != null){
            sessionID.setText(model.getSessID());
        }

        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplication.setRestaurants(new ArrayList<Restaurant>());
    }

    View.OnClickListener btnStartVetoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CaptainController tmp = mApplication.getCaptainController();

            mApplication.getCaptainController().startVetoProcess(distanceText.getText().toString());
        }
    };

    // Update session id when the server sends one
    // If the session parameter is not empty, then set the text to that immediately
    // else just get the session id from the model itself
    public void updateSessionID(String session){
        if (!session.isEmpty()) {
            sessionID.setText(session);
        } else {
            sessionID.setText(model.getSessID());
        }
    }

    public void updateNumUsersField(String numUsers) {
        if (!numUsers.isEmpty()) {
            userCount.setText(numUsers);
        } else {
            userCount.setText(model.getNumUsers());
        }
    }

    private void initListeners(){
        btnStart.setOnClickListener(btnStartVetoClickListener);
    }

}
