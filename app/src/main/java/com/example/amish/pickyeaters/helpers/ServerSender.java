package com.example.amish.pickyeaters.helpers;

import com.example.amish.pickyeaters.application;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akshat on 2016-07-05.
 */
public class ServerSender {
    private application mApplication;
    private Socket mSocket;

    public ServerSender(){
        mApplication = application.getInstance();
        mSocket = mApplication.getSocket();
    }

    public void sendMessage(String message) {
        mSocket.emit("createSession");
    }

    public void sendMessage(ArrayList<JSONObject> obj){
        mSocket.emit("createSession", obj);
    }
}
