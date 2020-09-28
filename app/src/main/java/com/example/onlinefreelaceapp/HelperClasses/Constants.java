package com.example.onlinefreelaceapp.HelperClasses;

public class Constants {

    //db name
    public static final String DB_NAME = "GIG_INFO_DB";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "GIG_INFO_TABLE ";
    //table columns
    public static final String G_ID = "ID ";
    public static final String G_TITLE = "TITLE";
    public static final String G_CATEGORY = "CATEGORY";
    public static final String G_DESCRIPTION = "DESCRIPTION";
    public static final String G_DELIVERYINFO = "DELINFO";
    public static final String G_ADVANCE_AMOUNT = "ADVANCE";
    public static final String G_SECOND_PAYMENT = "SECOND_PAYMENT";
    public static final String G_CONTACTINFO = "CONINFO";
    public static final String G_IMAGE = "IMAGE";
    public static final String G_ADD_TIMESTAMP = "ADD_TIMESTAMP";
    public static final String G_UPDATE_TIMESTAMP = "UPDATE_TIMESTAM";

    public static final String BUNDLE_ID = "bdlPK";

    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "("
            + G_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + G_TITLE + " TEXT,"
            + G_CATEGORY + " TEXT,"
            + G_DESCRIPTION + " TEXT,"
            + G_DELIVERYINFO + "TEXT,"
            + G_ADVANCE_AMOUNT + " TEXT,"
            + G_SECOND_PAYMENT + " TEXT,"
            + G_CONTACTINFO + " TEXT,"
            + G_IMAGE + " TEXT,"
            + G_ADD_TIMESTAMP + " TEXT,"
            + G_UPDATE_TIMESTAMP + " TEXT,"
            + ")";

}
