package com.example.onlinefreelaceapp;

public class Deliver {

    private int ID;
    private String resource,massage,seller,buyer;
    private int gigId;

    public Deliver(int ID, String resource, String massage, String seller, String buyer, int gigId) {
        this.ID = ID;
        this.resource = resource;
        this.massage = massage;
        this.seller = seller;
        this.buyer = buyer;
        this.gigId = gigId;
    }

    public Deliver(String resource, String massage, String seller, String buyer, int gigId) {
        this.resource = resource;
        this.massage = massage;
        this.seller = seller;
        this.buyer = buyer;
        this.gigId = gigId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getGigId() {
        return gigId;
    }

    public void setGigId(int gigId) {
        this.gigId = gigId;
    }
}
