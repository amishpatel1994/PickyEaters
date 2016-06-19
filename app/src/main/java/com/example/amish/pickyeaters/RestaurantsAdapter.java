package com.example.amish.pickyeaters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Akshat on 2016-06-11.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
    private List<Restaurant> restaurantList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating, phone;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
            phone = (TextView) view.findViewById(R.id.phone);
        }
    }


    public RestaurantsAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    public int getIndexByName(String name) {
        for (int i = 0; i < restaurantList.size(); i++) {
            Restaurant rest = restaurantList.get(i);
            if (rest.getName() == name) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.rating.setText(restaurant.getRating().toString());
        holder.phone.setText(restaurant.getPhone());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurantName = (String) ((TextView) view).getText();
                int targetLocation = getIndexByName(restaurantName);
                if (targetLocation >= 0) {
                    restaurantList.remove(targetLocation);
                    notifyDataSetChanged();
                }
                Log.d("THIS NIGGA clicked", restaurantName);
            }
        });


    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
