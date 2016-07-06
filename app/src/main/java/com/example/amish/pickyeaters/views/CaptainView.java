package com.example.amish.pickyeaters.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;
import com.example.amish.pickyeaters.controllers.CaptainController;
import com.example.amish.pickyeaters.models.CaptainModel;

/**
 * Created by Akshat on 2016-06-24.
 */
public class CaptainView extends AppCompatActivity {

    application mApplication;
    Button btnStart;
    TextView sessionID;
    EditText distanceText;
    TextView userCount;
    CaptainModel model;

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

    View.OnClickListener btnStartVetoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CaptainController tmp = mApplication.getCaptainController();

            mApplication.getCaptainController().startVetoProcess(distanceText.getText().toString());
        }
    };

    public void updateSessionID(String sessID){
        sessionID.setText(sessID);
    }

    private void initListeners(){
        btnStart.setOnClickListener(btnStartVetoClickListener);
    }

}
