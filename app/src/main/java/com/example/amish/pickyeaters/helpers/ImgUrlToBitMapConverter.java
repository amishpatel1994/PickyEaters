package com.example.amish.pickyeaters.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

/**
 * Created by amish on 06/07/16.
 */
public class ImgUrlToBitMapConverter {
    public static Bitmap convert(String url) {
        try {
            URL newurl = new URL(url);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            return mIcon_val;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
