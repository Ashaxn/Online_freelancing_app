package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "onlinefreelance.db";

    public static final String COLUMN_NAME_ORDER_WORKEMAIL = "workemail";
    public static final String COLUMN_NAME_ORDER_ID = "id";
    public static final String COLUMN_NAME_ORDER_RESOURCE = "resource";
    public static final String COLUMN_NAME_ORDER_REQ = "requirement";
    public static final String COLUMN_NAME_ORDER_SUBTOT = "subtotal";
    public static final String COLUMN_NAME_ORDER_SERVICECHARGE = "servicecharge";
    public static final String COLUMN_NAME_ORDER_TOT = "total";
    public static final String COLUMN_NAME_ORDER_BUYER = "buyer";
    public static final String COLUMN_NAME_ORDER_SELLER = "seller";
    public static final String COLUMN_NAME_ORDER_GIG = "gig";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        //create users table
        MyDB.execSQL("create table users(id INTEGER primary key  AUTOINCREMENT,username TEXT,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");

        //create order table
        MyDB.execSQL(("create table orders(id INTEGER primary key AUTOINCREMENT,workemail TEXT,requirement TEXT,resource TEXT,subtotal INTEGER,servicecharge INTEGER,total INTEGER,buyer TEXT,seller TEXT,gigid INTEGER)"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertUser(String username,String fullname,String email,String mobilenumber,String province,int year,int month,int day,String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("fullname",fullname);
        contentValues.put("email",email);
        contentValues.put("mobilenumber",mobilenumber);
        contentValues.put("province",province);
        contentValues.put("year",year);
        contentValues.put("month",month);
        contentValues.put("day",day);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean cheackUser(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=?",new String[] {username});

        if(cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username,String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }

    public int getUserID(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select id from users where username=?",new String[] {username});

        if(cursor.getCount()>0 && cursor.moveToFirst()) {
            int userid = cursor.getInt(0);
            MyDB.close();
            return userid;
        }else {
            MyDB.close();
            return -99;
        }
    }


    public String getUserEmail(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select email from users where username=?",new String[] {username});

        if(cursor.getCount()>0 && cursor.moveToFirst()) {
            String email = cursor.getString(0);
            MyDB.close();
            return email;
        }else {
            MyDB.close();
            return "Enter Working Email";
        }
    }

    //Order Section
    //Insert Order
    public Boolean insertOrder(String email,String req,String resource,int subtotal,int total,int service,String buyer,String seller,int gigid) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("workemail",email);
        contentValues.put("requirement",req);
        contentValues.put("resource",resource);
        contentValues.put("subtotal",subtotal);
        contentValues.put("servicecharge",service);
        contentValues.put("total",total);
        contentValues.put("buyer",buyer);
        contentValues.put("seller",seller);
        contentValues.put("gigid",gigid);

        long result = MyDB.insert("orders",null,contentValues);

        if(result==-1) {
            return false;
        }else {
            return true;
        }

    }

    //list orders to me list based on sellerid
    public ArrayList<HashMap<String, String>> getOrderList(String sellerid) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();

        Cursor cursor = MyDB.rawQuery("select * from orders where TRIM(seller) = '"+sellerid.trim()+"'",null);

        while (cursor.moveToNext()) {
            HashMap<String,String> order = new HashMap<>();

            order.put("orderid", Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
            order.put("buyerid",cursor.getString(cursor.getColumnIndex("buyer")));
            order.put("sellerid",cursor.getString(cursor.getColumnIndex("seller")));
            order.put("email",cursor.getString(cursor.getColumnIndex("workemail")));
            order.put("total",Integer.toString(cursor.getInt(cursor.getColumnIndex("total"))));
            order.put("resource",cursor.getString(cursor.getColumnIndex("resource")));
            order.put("requirement",cursor.getString(cursor.getColumnIndex("requirement")));

            orderList.add(order);
        }

        return orderList;

    }

    public ArrayList<HashMap<String, String>> getBuyerMyOrderList(String buyerid) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();

        Cursor cursor = MyDB.rawQuery("select * from orders where TRIM(buyer) = '"+buyerid.trim()+"'",null);

        while (cursor.moveToNext()) {
            HashMap<String,String> order = new HashMap<>();

            order.put("orderid", Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
            order.put("buyerid",cursor.getString(cursor.getColumnIndex("buyer")));
            order.put("sellerid",cursor.getString(cursor.getColumnIndex("seller")));
            order.put("email",cursor.getString(cursor.getColumnIndex("workemail")));
            order.put("total",Integer.toString(cursor.getInt(cursor.getColumnIndex("total"))));
            order.put("resource",cursor.getString(cursor.getColumnIndex("resource")));
            order.put("requirement",cursor.getString(cursor.getColumnIndex("requirement")));

            orderList.add(order);
        }

        return orderList;

    }

}
