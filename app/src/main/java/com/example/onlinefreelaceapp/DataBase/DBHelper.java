package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "onlinefreelance.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table users(username TEXT primary key,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");

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
}
