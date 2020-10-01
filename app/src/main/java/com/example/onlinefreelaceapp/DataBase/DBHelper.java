package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.HelperClasses.PrefManager;
import com.example.onlinefreelaceapp.adapter.GigHolder;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "onlinefreelance.db";
    private PrefManager prefManager;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        prefManager = new PrefManager(context);

    }

    SQLiteDatabase localDatabase;

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table users(username TEXT primary key,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");
        MyDB.execSQL("create table gigs(id integer primary key autoincrement," +
                "title TEXT,category TEXT,description TEXT,deliver TEXT,advanceAmount TEXT," +
                "secondPayment TEXT,contact TEXT,image TEXT,timestamp TEXT,username TEXT)");

        localDatabase = MyDB;


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists gigs");
    }

    public Boolean insertUser(String username, String fullname, String email, String mobilenumber, String province, int year, int month, int day, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("mobilenumber", mobilenumber);
        contentValues.put("province", province);
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("day", day);
        contentValues.put("password", password);
        long cursorult = MyDB.insert("users", null, contentValues);
        if (cursorult == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean cheackUser(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getFullName(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});

        if (cursor.moveToNext()) {
            return cursor.getString(1);
        } else {
            return null;
        }
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


//    ---------create a gig

    public void saveGig(String title, String category, String description, String delivery, String advanceAmount, String secondPayment,
                        String contact, String imageUrl) {

        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }


        localDatabase.execSQL("insert into gigs(title,category,description ,deliver ,advanceAmount , secondPayment ,contact,image,timestamp,username ) " +
                "values('" + title + "','" + category + "','" + description + "','" + delivery + "','" + advanceAmount + "','" +
                secondPayment + "','" + contact + "','" + imageUrl + "','" + System.currentTimeMillis() + "','" + prefManager.getString(Constants.CURRENT_USER) + "')");


    }


    public void updateGig(int id, String title, String category, String description, String delivery, String advanceAmount, String secondPayment,
                          String contact, String imageUrl) {

        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }

        localDatabase.execSQL("update gigs set title='" + title + "',category='" + category + "', description='" + description + "' ,deliver ='" + delivery + "'," +
                " advanceAmount ='" + advanceAmount + "', secondPayment ='" +
                secondPayment + "',contact='" + contact + "',image='" + imageUrl + "',timestamp ='" + System.currentTimeMillis() + "',username='" + prefManager.getString(Constants.CURRENT_USER) + "' where id = '" + id + "'");


    }


    public List<GigHolder> getAllGigs() {
        List<GigHolder> list = new ArrayList<>();
        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }

        Cursor cursor = localDatabase.rawQuery("select * from gigs", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);   //0 is the number of id column in your database table
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);
            String delivery = cursor.getString(4);
            String advanceAmount = cursor.getString(5);
            String secondPayment = cursor.getString(6);
            String contact = cursor.getString(7);
            String image = cursor.getString(8);
            String time = cursor.getString(9);
            String username = cursor.getString(10);

            GigHolder holder = new GigHolder(id, title, category, description, delivery, advanceAmount, secondPayment, contact, Uri.parse(image), time, username);
            list.add(holder);
        }

        return list;
    }

    public GigHolder getGigsFromPrimaryKey(int primaryKey) {
        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }

        Cursor cursor = localDatabase.rawQuery("select * from gigs where id='" + primaryKey + "'", null);
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);   //0 is the number of id column in your database table
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);
            String delivery = cursor.getString(4);
            String advanceAmount = cursor.getString(5);
            String secondPayment = cursor.getString(6);
            String contact = cursor.getString(7);
            String image = cursor.getString(8);
            String time = cursor.getString(9);
            String username = cursor.getString(10);

            GigHolder holder = new GigHolder(id, title, category, description, delivery, advanceAmount, secondPayment, contact, Uri.parse(image), time, username);
            return holder;
        }

        return null;
    }


    public void deleteGig(int primaryKey) {
        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }

        localDatabase.execSQL("delete from gigs where id=  '" + primaryKey + "' ");
    }


}
