package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.onlinefreelaceapp.Common.LoginSignup.UsersModel;
import com.example.onlinefreelaceapp.Common.PostRequestModel;
import com.example.onlinefreelaceapp.Orders;
import androidx.annotation.Nullable;
import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.HelperClasses.PrefManager;

import com.example.onlinefreelaceapp.adapter.GigHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "onlinefreelance.db";
    private static final String POST_TABLE_NAME = "postrequest";
    private static final String USERS_TABLE_NAME = "users";


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
    private static final String POST_USERNAME = "username";



    private static final int VERSION = 1;
    private PrefManager prefManager;

    //Orders
    public static final String TABLE_NAME_ORDER = "orders";
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
    public static final String COLUMN_NAME_ORDER_SUBMITDATE = "publish";
    public static final String COLUMN_NAME_ORDER_FINISHDATE = "finish";



    // Users Column Names
    private static final String Prof_User = "username";
    private static final String Prof_Full = "fullname";
    private static final String Prof_email = "email";
    private static final String Prof_mobile = "mobilenumber";
    private static final String Prof_prov = "province";
    private static final String Prof_year = "year";
    private static final String Prof_month = "month";
    private static final String Prof_day = "day";
    private static final String Prof_pass = "password";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        prefManager = new PrefManager(context);
    }



    SQLiteDatabase localDatabase;

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        //create users table
       // MyDB.execSQL("create table users(id INTEGER primary key  AUTOINCREMENT,username TEXT,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");

        MyDB.execSQL("create table gigs(id integer primary key autoincrement," +
                "title TEXT,category TEXT,description TEXT,deliver TEXT,advanceAmount TEXT," +
                "secondPayment TEXT,contact TEXT,image TEXT,timestamp TEXT,username TEXT)");

        String TABLE_CREATE_QUERY_ORDER = "CREATE TABLE "+TABLE_NAME_ORDER+ " "+
                "("
                +COLUMN_NAME_ORDER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_NAME_ORDER_WORKEMAIL+ " TEXT,"
                +COLUMN_NAME_ORDER_REQ+ " TEXT,"
                +COLUMN_NAME_ORDER_RESOURCE+ " TEXT,"
                +COLUMN_NAME_ORDER_SUBTOT+ " INTEGER,"
                +COLUMN_NAME_ORDER_SERVICECHARGE+ " INTEGER,"
                +COLUMN_NAME_ORDER_TOT+ " INTEGER,"
                +COLUMN_NAME_ORDER_BUYER+ " TEXT,"
                +COLUMN_NAME_ORDER_SELLER+ " TEXT,"
                +COLUMN_NAME_ORDER_GIG+ " INTEGER,"
                +COLUMN_NAME_ORDER_SUBMITDATE+ " TEXT,"
                +COLUMN_NAME_ORDER_FINISHDATE+ " TEXT"+
                ");";

        //run query
        MyDB.execSQL(TABLE_CREATE_QUERY_ORDER);



        localDatabase = MyDB;
        MyDB.execSQL("create table contactus(conid INTEGER primary key AUTOINCREMENT,name TEXT,Description TEXT)");
       // MyDB.execSQL("create table users(username TEXT primary key,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");


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
                +POST_BUDGET + " TEXT,"
                +POST_USERNAME + " TEXT"+
                ");";

        //Post Request Table Run
        MyDB.execSQL(POST_REQUEST_TABLE_CREATE_QUERY);

        // Users Table Create
        String SIGN_UP_CREATE_QUERY = "CREATE TABLE "+USERS_TABLE_NAME+" " +
                "("
                +Prof_User+ " TEXT PRIMARY KEY UNIQUE ,"
                +Prof_Full + " TEXT,"
                +Prof_email + " TEXT,"
                +Prof_mobile+ " TEXT,"
                +Prof_prov+ " TEXT,"
                +Prof_year+" INTEGER,"
                +Prof_month+" INTEGER,"
                +Prof_day+ " INTEGER,"
                +Prof_pass + " TEXT" +
                ");";

        //Users Table Run
        MyDB.execSQL(SIGN_UP_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
       // MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists gigs");

        //drop table query orders
        String DROP_TABLE_ORDERS = "DROP TABLE IF EXISTS " +TABLE_NAME_ORDER;

        //POST Request If Exists QUERY
        String DROP_POST_REQUEST_TABLE_QUERY = "DROP TABLE IF EXISTS "+ POST_TABLE_NAME;
        MyDB.execSQL(DROP_POST_REQUEST_TABLE_QUERY);
        //onCreate(MyDB);

        //Users If Exists QUERY
        String DROP_SIGN_UP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ USERS_TABLE_NAME;
        MyDB.execSQL(DROP_SIGN_UP_TABLE_QUERY);
        onCreate(MyDB);

        //exe query
        MyDB.execSQL(DROP_TABLE_ORDERS);

       // onCreate(MyDB);


    }


    // Users Table (Add)
    public boolean insertUsers(UsersModel usersModel){
        SQLiteDatabase MyDB = getWritableDatabase();

       ContentValues contentValues = new ContentValues();

        contentValues.put(Prof_User, usersModel.getUsername());
        contentValues.put(Prof_Full,usersModel.getFullname());
        contentValues.put(Prof_email,usersModel.getEmail());
        contentValues.put(Prof_mobile,usersModel.getMobilenumber());
        contentValues.put(Prof_prov,usersModel.getProvince());
        contentValues.put(Prof_year,usersModel.getYear());
        contentValues.put(Prof_month,usersModel.getMonth());
        contentValues.put(Prof_day,usersModel.getDay());
        contentValues.put(Prof_pass,usersModel.getPassword());

        // Save Data To Table
        long result = MyDB.insert(USERS_TABLE_NAME,null,contentValues);
        MyDB.close();
        if (result==1){
            return false;
        }
        else{
            return true;
        }

    }

    // Get  Profile View
    public UsersModel getProfileView(String username){
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.query(USERS_TABLE_NAME,new String[]{Prof_User,Prof_Full,Prof_email,Prof_prov,Prof_year,Prof_month,Prof_day,Prof_mobile},Prof_User + "= ?",new String[]{username},null, null,null);

        UsersModel usersModel;
        if(cursor != null){
            cursor.moveToFirst();
            usersModel = new UsersModel(
                    cursor.getString(0),               //Prof_user
                    cursor.getString(1),            //Prof_full
                    cursor.getString(2),            //Prof_email
                    cursor.getString(3),            //prof_prov
                    cursor.getInt(4),               //Prof_Year
                    cursor.getInt(5),               //Prof_Month
                    cursor.getInt(6),              //Prof_Day
                    cursor.getString(7)             //Prof_mobile
            );
            return usersModel;
        }
        return null;
    }

    //Delete profile
    public void deleteProfile(String username){
        SQLiteDatabase MyDB = getWritableDatabase();
        MyDB.delete(USERS_TABLE_NAME,Prof_User +" =?",new String[]{username});
        MyDB.close();



    }


    // Update Profile
    public  int updateProfile(UsersModel usersModel ){
        SQLiteDatabase MyDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Prof_Full,usersModel.getFullname());
        contentValues.put(Prof_email,usersModel.getEmail());
        contentValues.put(Prof_prov,usersModel.getProvince());
        contentValues.put(Prof_year,usersModel.getYear());
        contentValues.put(Prof_month,usersModel.getMonth());
        contentValues.put(Prof_day,usersModel.getDay());
        contentValues.put(Prof_mobile,usersModel.getMobilenumber());


        int status = MyDB.update(USERS_TABLE_NAME,contentValues,Prof_User +" =?",new String[]{String.valueOf(usersModel.getUsername())});
        MyDB.close();
        return status;
    }



    public Boolean checkUser(String username) {
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
    /*
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

    }*/

    public Boolean insertOrder(Orders order) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_ORDER_WORKEMAIL,order.getWork_email());
        contentValues.put(COLUMN_NAME_ORDER_REQ,order.getReq());
        contentValues.put(COLUMN_NAME_ORDER_RESOURCE,order.getResource());
        contentValues.put(COLUMN_NAME_ORDER_SUBTOT,order.getSubTot());
        contentValues.put(COLUMN_NAME_ORDER_SERVICECHARGE,order.getServiceCharge());
        contentValues.put(COLUMN_NAME_ORDER_TOT,order.getTotal());
        contentValues.put(COLUMN_NAME_ORDER_BUYER,order.getBuyer());
        contentValues.put(COLUMN_NAME_ORDER_SELLER,order.getSeller());
        contentValues.put(COLUMN_NAME_ORDER_GIG,order.getGigID());
        contentValues.put(COLUMN_NAME_ORDER_SUBMITDATE,order.getStarteddate());
        contentValues.put(COLUMN_NAME_ORDER_FINISHDATE,order.getFinishdate());

        long result = MyDB.insert(TABLE_NAME_ORDER,null,contentValues);

        MyDB.close();

        if(result==-1) {
            return false;
        }else {
            return true;
        }

    }

    //get Count Order orders to me processing
    public int getCountOrdersToBeRecProcessing(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_ORDER+" WHERE TRIM("+COLUMN_NAME_ORDER_BUYER+") = '"+username.trim()+"'";

        Cursor cursor = MyDB.rawQuery(query,null);
        return cursor.getCount();
    }

    //get count orders to me
    public int getCountOrdersToMeProcessing(String username)  {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_ORDER+" WHERE TRIM("+COLUMN_NAME_ORDER_SELLER+") = '"+username.trim()+"'";

        Cursor cursor = MyDB.rawQuery(query,null);
        return cursor.getCount();
    }

/*
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

    }*/

    public List<Orders> getBuyerMyOrderList(String username) {

        List<Orders> ordersList = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_ORDER+" WHERE TRIM("+COLUMN_NAME_ORDER_BUYER+") = '"+username.trim()+"'";

        Cursor cursor= MyDB.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                Orders order = new Orders();
                order.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_ID)));
                order.setWork_email(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_WORKEMAIL)));
                order.setReq(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_REQ)));
                order.setResource(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_RESOURCE)));
                order.setSubTot(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBTOT)));
                order.setServiceCharge(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SERVICECHARGE)));
                order.setTotal(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_TOT)));
                order.setBuyer(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_BUYER)));
                order.setSeller(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SELLER)));
                order.setGigID(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_GIG)));
                order.setStarteddate(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBMITDATE)));
                order.setFinishdate(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_FINISHDATE)));

                ordersList.add(order);
            }while (cursor.moveToNext());
        }
        MyDB.close();
        return ordersList;


    }


    public List<Orders> getSellerMyOrderList(String username) {

        List<Orders> ordersList = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_ORDER+" WHERE TRIM("+COLUMN_NAME_ORDER_SELLER+") = '"+username.trim()+"'";

        Cursor cursor= MyDB.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                Orders order = new Orders();
                order.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_ID)));
                order.setWork_email(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_WORKEMAIL)));
                order.setReq(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_REQ)));
                order.setResource(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_RESOURCE)));
                order.setSubTot(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBTOT)));
                order.setServiceCharge(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SERVICECHARGE)));
                order.setTotal(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_TOT)));
                order.setBuyer(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_BUYER)));
                order.setSeller(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SELLER)));
                order.setGigID(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_GIG)));
                order.setStarteddate(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBMITDATE)));
                order.setFinishdate(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_FINISHDATE)));

                ordersList.add(order);
            }while (cursor.moveToNext());
        }
        MyDB.close();
        return ordersList;


    }


    public void deleteOrdersToBeRec(int id) {
        SQLiteDatabase MyDB = getWritableDatabase();
        MyDB.delete(TABLE_NAME_ORDER,"id =?",new String[]{String.valueOf(id)});
        MyDB.close();
    }

    public Orders getSingleOrderToBeRec(int id) {
        SQLiteDatabase MyDB = getWritableDatabase();

        //String query = "SELECT * FROM "+TABLE_NAME_ORDER+" WHERE TRIM("+COLUMN_NAME_ORDER_BUYER+") = '"+String.valueOf(id).trim()+"'";


        //Cursor cursor = MyDB.rawQuery(query,null);
        Cursor cursor = MyDB.query(TABLE_NAME_ORDER,new String[]{COLUMN_NAME_ORDER_ID,COLUMN_NAME_ORDER_WORKEMAIL,COLUMN_NAME_ORDER_REQ,COLUMN_NAME_ORDER_RESOURCE,COLUMN_NAME_ORDER_SUBTOT,COLUMN_NAME_ORDER_SERVICECHARGE,COLUMN_NAME_ORDER_TOT,COLUMN_NAME_ORDER_BUYER,COLUMN_NAME_ORDER_SELLER,COLUMN_NAME_ORDER_GIG,COLUMN_NAME_ORDER_SUBMITDATE,COLUMN_NAME_ORDER_FINISHDATE},COLUMN_NAME_ORDER_ID+ "= ?",
                new String[]{String.valueOf(id)},null,null,null);


        cursor.moveToFirst();

        //Orders order;
        if(cursor!=null) {
                /*order = new Orders(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBTOT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SERVICECHARGE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_TOT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_GIG)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_WORKEMAIL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_RESOURCE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_REQ)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_BUYER)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SELLER)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBMITDATE)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_FINISHDATE))
                );*/

            Orders order = new Orders();
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_ID))));
            order.setWork_email(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_WORKEMAIL)));
            order.setReq(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_REQ)));
            order.setResource(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_RESOURCE)));
            order.setSubTot(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBTOT))));
            order.setServiceCharge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SERVICECHARGE))));
            order.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_TOT))));
            order.setBuyer(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_BUYER)));
            order.setSeller(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SELLER)));
            order.setGigID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_GIG))));
            order.setStarteddate(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBMITDATE))));
            order.setFinishdate(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_FINISHDATE))));


            return order;
        }
        return null;

      /*  Orders order;

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            order = new Orders(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_ID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBTOT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_SERVICECHARGE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_TOT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ORDER_GIG)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_WORKEMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_RESOURCE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_REQ)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_BUYER)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORDER_SELLER)),
                    cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_SUBMITDATE)),
                    cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_ORDER_FINISHDATE))
            );

            return order;
        }
        return null;*/
    }


 /*

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
*/

    //update order
    public int updateSingleOrdersToBeRecProcessing(int id, String email, String resource, String req) {
        SQLiteDatabase MyDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_ORDER_WORKEMAIL,email);
        contentValues.put(COLUMN_NAME_ORDER_RESOURCE,resource);
        contentValues.put(COLUMN_NAME_ORDER_REQ,req);

        int status = MyDB.update(TABLE_NAME_ORDER,contentValues,COLUMN_NAME_ORDER_ID +" =?",new String[]{String.valueOf(id)});

        MyDB.close();

        return  status;

    }








//    ---------create a gig


    public String getFullName(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});

        if (cursor.moveToNext()) {
            return cursor.getString(1);
        } else {
            return null;
        }
    }

    public String getUserEmailAddress(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});

        if (cursor.moveToNext()) {
            return cursor.getString(2);
        } else {
            return null;
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
        contentValues.put(POST_USERNAME,postRequestModel.getUsername());

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
    public List<PostRequestModel> getAllPosts(String username){

        List<PostRequestModel> requestPosts = new ArrayList();
        SQLiteDatabase MyDB = getReadableDatabase();
        String query = "SELECT * FROM "+POST_TABLE_NAME+" WHERE TRIM("+POST_USERNAME+") = '"+username.trim()+"'";

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

        Cursor cursor = MyDB.query(POST_TABLE_NAME,new String[]{POST_ID,POST_TITLE,POST_MOBILE,POST_CATEGORY,POST_YEAR,POST_MONTH,POST_DAY,POST_DESC,POST_BUDGET,POST_USERNAME},POST_ID + "= ?",new String[]{String.valueOf(id)},null, null,null);

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
                    cursor.getString(8),            //Post_Budget
                    cursor.getString(9)             //Post_Username
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
                secondPayment + "',contact='" + contact + "',image='" + imageUrl + "',timestamp ='" + System.currentTimeMillis() + "' where id = '" + id + "'");


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


    public Boolean insertContactUs(String name,String description){

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("Description",description);
        long result = MyDB.insert("contactus",null,contentValues);
    if (result==1){
        return false;
    }
        else{
            return true;
    }

    }



    public int updatePassword(UsersModel usersModel) {
        SQLiteDatabase MyDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Prof_pass,usersModel.getPassword());

        int status = MyDB.update(USERS_TABLE_NAME,contentValues,Prof_User +" =?",new String[]{String.valueOf(usersModel.getUsername())});
        return status;

    }


}
