package com.example.amish.pickyeaters.helpers;

import android.util.Log;

import com.example.amish.pickyeaters.application;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

/**
 * Created by Akshat on 2016-07-05.
 */
public class ServerReciever {
    private application mApplication;
    private Socket mSocket;

    public ServerReciever(){
        mApplication = application.getInstance();
        mSocket = mApplication.getSocket();

        mSocket.on("created", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String sessionID = (String) args[0];
                Log.d("created", "Session ID is: " + sessionID);
                mApplication.getCaptainModel().setSessID(sessionID);

                //To check if captain controller was created already, if not the line of code will handle it.
                //This is just to eliminate race condition
                if(mApplication.getCaptainController() != null){
                    mApplication.getCaptainController().sessionIdUpdated(sessionID);
                }
            }
        });

        mSocket.on("vetoed", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                boolean userVoteCounted = (boolean) args[0];
                String restaurantName = (String) args[1];

                if (userVoteCounted){
                    int curVotes = mApplication.getVetoModel().getNumVotes();
                    curVotes--;
                    mApplication.getVetoModel().setNumVotes(curVotes);
                }
                Log.e("vetoed", " restaurant: " + restaurantName + "vetoed: " + userVoteCounted);
            }
        });

        mSocket.on("joined", new Emitter.Listener(){
           @Override
           public void call(Object... args) {
               if (mApplication.isCaptain()) {
                   mApplication.getCaptainModel().setNumUsers(args[0].toString());
                   if (mApplication.getCaptainController() != null) {
                        mApplication.getCaptainController().updateNumberOfUsers(args[0].toString());
                   }
               }
           }
        });

        mSocket.on("started", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                int numVotes = (int) args[0];
                mApplication.getVetoModel().setNumVotes(numVotes);

                String restaurantList = args[1].toString();

                // Parse the JSON String to List of restaurants
               if (!mApplication.isCaptain()) {
                   JSONParser jp = new JSONParser(restaurantList);
                   ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
                   try {
                       restaurants = jp.getRestaurantList();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

                   if (!restaurants.isEmpty()) {
                       for (Restaurant r : restaurants) {
                           r.setImageBitmap(ImgUrlToBitMapConverter.convert(r.getImageUrl()));
                       }
                       mApplication.getVetoController().populateWithRestaurants(restaurants);

                   } else {
                       Log.d("didn't work", "bruh");
                   }
               }
                Integer votes = mApplication.getVetoModel().getNumVotes();
                Log.d("NUMBERVotes", votes.toString());
            }
        });
    }
}
