package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.onlinefreelaceapp.Common.PostRequestModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String DB_NAME = "onlinefreelance.db";
    private static final String POST_TABLE_NAME = "postrequest";


    // Post Request Table Column Names
    private static final String POST_ID = "id";
    private static final String POST_TITLE = "posttitle";
    private static final String POST_MOBILE = "postmobile";
    private static final String POST_CATEGORY = "postcategory";
    private static final String POST_YEAR = "postyear";
    private static final String POST_MONTH = "postmonth";
    private static final String POST_DAY = "postday";
    private static final String POST_DESC = "postdesc";
    private static final String POST_BUDGET = "postbudget";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table users(username TEXT primary key,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");


        // Post Request Table Create
        String POST_REQUEST_TABLE_CREATE_QUERY = "CREATE TABLE "+POST_TABLE_NAME+" " +
                "("
                +POST_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +POST_TITLE + " TEXT,"
                +POST_MOBILE + " TEXT,"
                +POST_CATEGORY+ " TEXT,"
                +POST_YEAR+" INTEGER,"
                +POST_MONTH+" INTEGER,"
                +POST_DAY+" INTEGER,"
                +POST_DESC + " TEXT,"
                +POST_BUDGET + " TEXT" +
                ");";

        //Post Request Table Run
        MyDB.execSQL(POST_REQUEST_TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");

        //POST Request If Exists QUERY
        String DROP_POST_REQUEST_TABLE_QUERY = "DROP TABLE IF EXISTS "+ POST_TABLE_NAME;
        MyDB.execSQL(DROP_POST_REQUEST_TABLE_QUERY);
        onCreate(MyDB);

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



    // Post Request Table (Add)
    public void insertPostRequest(PostRequestModel postRequestModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(POST_TITLE,postRequestModel.getPosttitle());
        contentValues.put(POST_MOBILE,postRequestModel.getPostmobile());
        contentValues.put(POST_CATEGORY,postRequestModel.getPostcategory());
        contentValues.put(POST_YEAR,postRequestModel.getPostyear());
        contentValues.put(POST_MONTH,postRequestModel.getPostmonth());
        contentValues.put(POST_DAY,postRequestModel.getPostday());
        contentValues.put(POST_DESC,postRequestModel.getPostdesc());
        contentValues.put(POST_BUDGET,postRequestModel.getPostbudget());

        // Save Data To Table
        sqLiteDatabase.insert(POST_TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }


    // Count Post Request Table Data
    public int postCount(){
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+ POST_TABLE_NAME;

        Cursor cursor = MyDB.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get All Posts Into a List
    public List<PostRequestModel> getAllPosts(){

        List<PostRequestModel> requestPosts = new ArrayList();
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+POST_TABLE_NAME;

        Cursor cursor = MyDB.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                PostRequestModel postRequestModel = new PostRequestModel();

                postRequestModel.setId(cursor.getInt(0));
                postRequestModel.setPosttitle(cursor.getString(1));
                postRequestModel.setPostmobile(cursor.getString(2));
                postRequestModel.setPostcategory(cursor.getString(3));
                postRequestModel.setPostyear(cursor.getInt(4));
                postRequestModel.setPostmonth(cursor.getInt(5));
                postRequestModel.setPostday(cursor.getInt(6));
                postRequestModel.setPostdesc(cursor.getString(7));
                postRequestModel.setPostbudget(cursor.getString(8));

                requestPosts.add(postRequestModel);
            }while (cursor.moveToNext());
        }
        return requestPosts;
    }

    // Delete A Post Request
    public void deletePostRequest(int id){
        SQLiteDatabase MyDB = getWritableDatabase();
        MyDB.delete(POST_TABLE_NAME,POST_ID +" =?",new String[]{String.valueOf(id)});
        MyDB.close();
    }

    // Get  A Single Post Request
    public PostRequestModel getSinglePost(int id){
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.query(POST_TABLE_NAME,new String[]{POST_ID,POST_TITLE,POST_MOBILE,POST_CATEGORY,POST_YEAR,POST_MONTH,POST_DAY,POST_DESC,POST_BUDGET},POST_ID + "= ?",new String[]{String.valueOf(id)},null, null,null);

        PostRequestModel postRequestModel;
        if(cursor != null){
                cursor.moveToFirst();
            postRequestModel = new PostRequestModel(
                    cursor.getInt(0),               //ID
                    cursor.getString(1),            //Post_Title
                    cursor.getString(2),            //Post_Mobile
                    cursor.getString(3),            //Post_Category
                    cursor.getInt(4),               //Post_Year
                    cursor.getInt(5),               //Post_Month
                    cursor.getInt(6),               //Post_Day
                    cursor.getString(7),            //Post_Desc
                    cursor.getString(8)            //Post_Budget
            );
            return postRequestModel;
        }
        return null;
    }

    // Update A Single Post Request
    public  int updateSinglePost(PostRequestModel postRequestModel ){
        SQLiteDatabase MyDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(POST_TITLE,postRequestModel.getPosttitle());
        contentValues.put(POST_MOBILE,postRequestModel.getPostmobile());
        contentValues.put(POST_CATEGORY,postRequestModel.getPostcategory());
        contentValues.put(POST_YEAR,postRequestModel.getPostyear());
        contentValues.put(POST_MONTH,postRequestModel.getPostmonth());
        contentValues.put(POST_DAY,postRequestModel.getPostday());
        contentValues.put(POST_DESC,postRequestModel.getPostdesc());
        contentValues.put(POST_BUDGET,postRequestModel.getPostbudget());

        int status = MyDB.update(POST_TABLE_NAME,contentValues,POST_ID +" =?",new String[]{String.valueOf(postRequestModel.getId())});
        MyDB.close();
        return status;
    }


    // Get All Requests Into a List
    public List<PostRequestModel> getAllRequests(){

        List<PostRequestModel> buyerPosts = new ArrayList();
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+POST_TABLE_NAME;

        Cursor cursor = MyDB.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                PostRequestModel postRequestModel = new PostRequestModel();

                postRequestModel.setId(cursor.getInt(0));
                postRequestModel.setPosttitle(cursor.getString(1));
                postRequestModel.setPostmobile(cursor.getString(2));
                postRequestModel.setPostcategory(cursor.getString(3));
                postRequestModel.setPostyear(cursor.getInt(4));
                postRequestModel.setPostmonth(cursor.getInt(5));
                postRequestModel.setPostday(cursor.getInt(6));
                postRequestModel.setPostdesc(cursor.getString(7));
                postRequestModel.setPostbudget(cursor.getString(8));

                buyerPosts.add(postRequestModel);
            }while (cursor.moveToNext());
        }
        return buyerPosts;
    }



}
