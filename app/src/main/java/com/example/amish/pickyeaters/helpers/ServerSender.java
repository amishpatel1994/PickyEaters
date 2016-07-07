package com.example.amish.pickyeaters.helpers;

import android.util.Log;

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
        Log.d("ServerSender,", message);
        mSocket.emit(message);
    }

    public void sendMessage(String message, String value) {
        Log.d("Send", "The message: " + message + ", value: " + value);
        mSocket.emit(message, value);
    }

    public void sendMessage(String message, ArrayList<JSONObject> obj){
        mSocket.emit(message, obj);
    }
}
