package com.example.amish.pickyeaters.helpers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amish.pickyeaters.R;
import com.example.amish.pickyeaters.application;

import java.util.ArrayList;

/**
 * Created by Akshat on 2016-06-11.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
    private Activity activity;
    private application mApplication;
    private ServerSender serverSender;
    public SparseBooleanArray selectedItems;
    //Number of restaurants that are Vetoed

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating, description, distance;
        public ImageView restaurantImage;

        public MyViewHolder(View view) {
            super(view);
            serverSender = new ServerSender();

            name = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
            description = (TextView) view.findViewById(R.id.description);
            distance = (TextView) view.findViewById(R.id.distance);
            restaurantImage = (ImageView) view.findViewById(R.id.thumbnailImg);
        }

    }


    public RestaurantsAdapter(ArrayList<Restaurant> restaurantList, Activity activity) {
        this.activity = activity;
        mApplication = (application) activity.getApplication();
        mApplication.restaurants = restaurantList;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Restaurant restaurant = mApplication.restaurants.get(position);
        holder.name.setText(restaurant.getName());
        holder.rating.setText(restaurant.getRating().toString());
        holder.distance.setText(restaurant.getDistance().toString() + " Km");
        holder.description.setText(restaurant.getDescription());
        holder.restaurantImage.setImageBitmap(restaurant.getImageBitmap());
        holder.itemView.setSelected(selectedItems.get(position, true));

        if (!selectedItems.get(position)) {
            holder.itemView.setSelected(false);
        }else{
            holder.itemView.setSelected(true);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numVetoesLeft = mApplication.getVetoModel().getNumVotes();

                int numVetoed = 0;
                for (Restaurant r:mApplication.restaurants) {
                    if (r.isVetoed()) {
                        numVetoed++;
                    }
                }

                if (numVetoesLeft >= 1) {
                    if (!selectedItems.get(position)) {
                        holder.itemView.setSelected(true);
                        mApplication.restaurants.get(position).setVetoed(true);
                        selectedItems.put(position, true);
                        serverSender.sendMessage("veto", mApplication.restaurants.get(position).getName());
                    } else {
                        holder.itemView.setSelected(false);
                    }
                }

                if (numVetoed == mApplication.restaurants.size()-2) {
                    Toast.makeText(activity, "Click on restaurant to go to google maps",
                            Toast.LENGTH_LONG).show();
                } else if (numVetoed == mApplication.restaurants.size()-1) {
                    String address = mApplication.restaurants.get(position).getAddress();
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.getApplication().startActivity(mapIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mApplication.restaurants.size();
    }
}
