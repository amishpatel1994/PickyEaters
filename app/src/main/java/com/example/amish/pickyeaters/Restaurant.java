package com.example.amish.pickyeaters;

/**
 * Created by Akshat on 2016-06-11.
 */
public class Restaurant {

    private String id;
    private String name;
    private String phone;
    private Double rating;
    private String address;
    private String imageUrl;
    private Double distance;
    private String description;

    Restaurant(String id, String name, String phone, String address, String url, String categories, Double rating, Double distance){
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.id = id;
        this.address = address;
        this.imageUrl = url;
        this.description = categories;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Restaurant)){
            return false;
        }

        Restaurant restaurant = (Restaurant) object;

        if (this.getId() == restaurant.getId()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        int result = getId().hashCode();
        return result;
    }

    public  String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public  void setAddress(String addr) {
        this.address = addr;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    
}
