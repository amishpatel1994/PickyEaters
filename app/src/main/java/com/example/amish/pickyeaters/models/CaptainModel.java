package com.example.amish.pickyeaters.models;

/**
 * Created by amish on 30/06/16.
 */
public class CaptainModel {
    private int numUsers;
    private String sessID;

    public CaptainModel() {
        numUsers = 0;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }
    public String getSessID() {
        return sessID;
    }

    public void setSessID(String sessID) {
        this.sessID = sessID;
    }
}
