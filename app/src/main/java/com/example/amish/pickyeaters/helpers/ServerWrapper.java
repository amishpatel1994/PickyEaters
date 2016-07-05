package com.example.amish.pickyeaters.helpers;

import android.util.Log;

import java.net.URISyntaxException;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by amish on 05/07/16.
 */
public class ServerWrapper {
    private Socket mSocket;

    public ServerWrapper (String url) {
        try {
            Log.e("Before" , "hl");
            mSocket = IO.socket(url);
            Log.e("After" , ""+mSocket.connected());
        } catch (Exception e) {
            Log.e("Server Error", e.getMessage());
        }
    }

    public Socket initConnection() {
        Log.e("Server before you fuck", ""+mSocket.connected());
        mSocket.connect();
        Log.e("Server Connect you fuck", ""+mSocket.connected());
        return mSocket;
    }
}
