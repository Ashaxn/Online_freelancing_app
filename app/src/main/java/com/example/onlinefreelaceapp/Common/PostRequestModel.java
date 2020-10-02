package com.example.onlinefreelaceapp.Common;

public class PostRequestModel {
    private int id, postyear, postmonth, postday;
    private String posttitle, postmobile, postcategory, postdesc, postbudget,username;


    public PostRequestModel(){

    }

    public PostRequestModel(int id, String posttitle, String postmobile, String postcategory, int postyear, int postmonth, int postday, String postdesc, String postbudget,String username) {
        this.id = id;
        this.postyear = postyear;
        this.postmonth = postmonth;
        this.postday = postday;
        this.posttitle = posttitle;
        this.postmobile = postmobile;
        this.postcategory = postcategory;
        this.postdesc = postdesc;
        this.postbudget = postbudget;
        this.username = username;
    }

    public PostRequestModel(int postyear, int postmonth, int postday, String posttitle, String postmobile, String postcategory, String postdesc, String postbudget,String username) {
        this.postyear = postyear;
        this.postmonth = postmonth;
        this.postday = postday;
        this.posttitle = posttitle;
        this.postmobile = postmobile;
        this.postcategory = postcategory;
        this.postdesc = postdesc;
        this.postbudget = postbudget;
        this.username = username;
    }

    public PostRequestModel(int id, int postyear, int postmonth, int postday, String posttitle, String postmobile, String postcategory, String postdesc, String postbudget) {
        this.id = id;
        this.postyear = postyear;
        this.postmonth = postmonth;
        this.postday = postday;
        this.posttitle = posttitle;
        this.postmobile = postmobile;
        this.postcategory = postcategory;
        this.postdesc = postdesc;
        this.postbudget = postbudget;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostyear() {
        return postyear;
    }

    public void setPostyear(int postyear) {
        this.postyear = postyear;
    }

    public int getPostmonth() {
        return postmonth;
    }

    public void setPostmonth(int postmonth) {
        this.postmonth = postmonth;
    }

    public int getPostday() {
        return postday;
    }

    public void setPostday(int postday) {
        this.postday = postday;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getPostmobile() {
        return postmobile;
    }

    public void setPostmobile(String postmobile) {
        this.postmobile = postmobile;
    }

    public String getPostcategory() {
        return postcategory;
    }

    public void setPostcategory(String postcategory) {
        this.postcategory = postcategory;
    }

    public String getPostdesc() {
        return postdesc;
    }

    public void setPostdesc(String postdesc) {
        this.postdesc = postdesc;
    }

    public String getPostbudget() {
        return postbudget;
    }

    public void setPostbudget(String postbudget) {
        this.postbudget = postbudget;
    }
}
