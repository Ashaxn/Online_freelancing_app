package com.example.onlinefreelaceapp.adapter;

import android.net.Uri;

public class GigHolder {

    private int primaryKey;
    private String title, category, description, deliveryInfo, advanceAmount, secondAmount, contact, time,username;
    private Uri image;

    public GigHolder(int primaryKey, String title, String category, String description, String deliveryInfo, String advanceAmount, String secondAmount, String contact, Uri image, String time,String username) {
        this.primaryKey = primaryKey;
        this.title = title;
        this.category = category;
        this.description = description;
        this.deliveryInfo = deliveryInfo;
        this.advanceAmount = advanceAmount;
        this.secondAmount = secondAmount;
        this.contact = contact;
        this.image = image;
        this.time = time;
        this.username = username;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getSecondAmount() {
        return secondAmount;
    }

    public void setSecondAmount(String secondAmount) {
        this.secondAmount = secondAmount;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotal() {
        return Double.parseDouble(advanceAmount) + Double.parseDouble(secondAmount);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
