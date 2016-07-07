package com.example.amish.pickyeaters.helpers;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshat on 2016-06-11.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
//    private List<Restaurant> restaurantList;
    private Activity activity;
    private application mApplication;
    private ServerSender serverSender;
    //Number of restaurants that are Vetoed
    private int count = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating, phone, description, distance;
        public CheckBox vetoCheckbox;
        public ImageView restaurantImage;

        public MyViewHolder(View view) {
            super(view);
            serverSender = new ServerSender();

            name = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
            description = (TextView) view.findViewById(R.id.description);
            distance = (TextView) view.findViewById(R.id.distance);
            //vetoCheckbox = (CheckBox) view.findViewById(R.id.vetoCheckBox);
            restaurantImage = (ImageView) view.findViewById(R.id.thumbnailImg);
        }
    }


    public RestaurantsAdapter(ArrayList<Restaurant> restaurantList, Activity activity) {
        this.activity = activity;
        mApplication = (application) activity.getApplication();
        mApplication.restaurants = restaurantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    public int getIndexByName(String name) {
        for (int i = 0; i < mApplication.restaurants.size(); i++) {
            Restaurant rest = mApplication.restaurants.get(i);
            if (rest.getName() == name) {
                return i;
            }
        }
        return -1;
    }

    View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            View mView = (View)(v.getParent().getParent());
            int listSize = mApplication.restaurants.size();

            TextView tv = (TextView) mView.findViewById(R.id.name);
            String restaurantName = (String) tv.getText();

            int targetLocation = getIndexByName(restaurantName);

            if(targetLocation >= 0){
                mApplication.restaurants.get(targetLocation).setVetoed(true);
                count++;
            }
            Log.e("tv ", restaurantName+" size: "+listSize+" count: "+count);

            //Take user to google maps if last item is clicked
            if (count == listSize){
                // Create a Uri from an intent string. Use the result to create an Intent.
                String address = mApplication.restaurants.get(targetLocation).getAddress();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.getApplication().startActivity(mapIntent);

            } else{
                //Not last item, grey it out
                serverSender.sendMessage("veto", restaurantName);
                mView.findViewById(R.id.row_list_layout).setAlpha(0.5f);
                mView.findViewById(R.id.row_list_layout).setBackgroundColor(Color.GRAY);
            }

            if (targetLocation >= 0 && count > 1) {
//                  mApplication.restaurants.get(targetLocation).setVetoed(true);
//                  notifyDataSetChanged();

                if (count == listSize-1){
                    Toast.makeText(activity, "Click on restaurant to go to google maps",
                            Toast.LENGTH_LONG).show();
                }
            }
            Log.d("clicked", restaurantName);
        }
    };

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Restaurant restaurant = mApplication.restaurants.get(position);
        holder.name.setText(restaurant.getName());
        holder.rating.setText(restaurant.getRating().toString());
        holder.distance.setText(restaurant.getDistance().toString() + " Km");
        holder.description.setText(restaurant.getDescription());
        holder.restaurantImage.setImageBitmap(restaurant.getImageBitmap());

        holder.name.setOnClickListener(clickListener);
        holder.rating.setOnClickListener(clickListener);
        holder.distance.setOnClickListener(clickListener);
        holder.description.setOnClickListener(clickListener);
        holder.restaurantImage.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return mApplication.restaurants.size();
    }
}
