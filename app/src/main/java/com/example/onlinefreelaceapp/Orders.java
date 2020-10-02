package com.example.onlinefreelaceapp;

public class Orders {

    private int id,subTot,serviceCharge,Total,gigID;
    private String work_email,resource,req,buyer,seller;
    private long starteddate,finishdate;

    public Orders() {
    }

    public Orders(int id, int subTot, int serviceCharge, int total, int gigID, String work_email, String resource, String req, String buyer, String seller, long starteddate, long finishdate) {
        this.id = id;
        this.subTot = subTot;
        this.serviceCharge = serviceCharge;
        Total = total;
        this.gigID = gigID;
        this.work_email = work_email;
        this.resource = resource;
        this.req = req;
        this.buyer = buyer;
        this.seller = seller;
        this.starteddate = starteddate;
        this.finishdate = finishdate;
    }

    public Orders(int subTot, int serviceCharge, int total, int gigID, String work_email, String resource, String req, String buyer, String seller,long starteddate, long finishdate) {
        this.subTot = subTot;
        this.serviceCharge = serviceCharge;
        Total = total;
        this.gigID = gigID;
        this.work_email = work_email;
        this.resource = resource;
        this.req = req;
        this.buyer = buyer;
        this.seller = seller;
        this.starteddate = starteddate;
        this.finishdate = finishdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubTot() {
        return subTot;
    }

    public void setSubTot(int subTot) {
        this.subTot = subTot;
    }

    public int getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getGigID() {
        return gigID;
    }

    public void setGigID(int gigID) {
        this.gigID = gigID;
    }

    public String getWork_email() {
        return work_email;
    }

    public void setWork_email(String work_email) {
        this.work_email = work_email;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public long getStarteddate() {
        return starteddate;
    }

    public void setStarteddate(long starteddate) {
        this.starteddate = starteddate;
    }

    public long getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(long finishdate) {
        this.finishdate = finishdate;
    }
}
