package com.example.amish.pickyeaters;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Akshat on 2016-06-22.
 */
public class application extends Application {
    private static application thisApp;

    public static application getInstance(){
        return thisApp;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        thisApp = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static String foo(){
        return "abra";
    }
}
