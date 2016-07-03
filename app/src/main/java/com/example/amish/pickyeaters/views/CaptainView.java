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

/**
 * Created by Akshat on 2016-06-24.
 */
public class CaptainView extends AppCompatActivity {

    application mApplication;
    Button btnStart;
    TextView sessionID;
    EditText distanceText;
    TextView userCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_view);
        mApplication = (application)getApplication();
        mApplication.setCaptainView(this);
        mApplication.initCaptainController();

        //Initialising View elements
        btnStart = (Button) findViewById(R.id.captain_startveto_button);
        sessionID = (TextView) findViewById(R.id.captain_session_id);
        distanceText = (EditText) findViewById(R.id.captain_distanceEditText);
        userCount = (TextView) findViewById(R.id.captain_userCount);

        initListeners();
    }

    View.OnClickListener btnStartVetoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CaptainController tmp = mApplication.getCaptainController();

            mApplication.getCaptainController().startVetoProcess(distanceText.getText().toString());
        }
    };

    private void initListeners(){
        btnStart.setOnClickListener(btnStartVetoClickListener);
    }

}
