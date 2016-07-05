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
            mSocket = IO.socket(url);
        } catch (Exception e) {
            Log.e("Server Error", e.getMessage());
        }
    }

    public Socket initConnection() {
        mSocket.connect();
        return mSocket;
    }
}
