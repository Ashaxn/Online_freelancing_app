package com.example.onlinefreelaceapp.Common.LoginSignup;

public class UsersModel {
    private int year, month, day;
    private String username, fullname, email, mobilenumber, province, password;


    //construtors

    public UsersModel(String username, String fullname, String email, String province ,int year, int month, int day, String mobilenumber) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.province = province;
        this.year = year;
        this.month = month;
        this.day = day;
        this.mobilenumber = mobilenumber;
    }

    public UsersModel(int year, int month, int day, String username, String fullname, String email, String mobilenumber, String province, String password) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.province = province;
        this.password = password;
    }

    public UsersModel(String username, String password) {
        this.username = username;
        this.password = password;
    }


    //getters and setters of Usermodel
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
