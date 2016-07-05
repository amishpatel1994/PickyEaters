package com.example.amish.pickyeaters.helpers;

import android.util.Log;

import com.example.amish.pickyeaters.application;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

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
                Log.e("created", "We just recieved created signal yo");
            }
        });
    }
}
