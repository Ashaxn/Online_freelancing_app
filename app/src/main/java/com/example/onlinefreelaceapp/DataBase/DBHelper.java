package com.example.onlinefreelaceapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.example.onlinefreelaceapp.adapter.GigHolder;


import com.example.onlinefreelaceapp.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "onlinefreelance.db";

    public static final int VERSION = 2;

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


    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    SQLiteDatabase localDatabase;

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

       // MyDB.execSQL("create table users(username TEXT primary key,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");


        MyDB.execSQL("drop Table if exists gigs");

        //create users table
        MyDB.execSQL("create table users(id INTEGER primary key  AUTOINCREMENT,username TEXT,fullname TEXT,email TEXT,mobilenumber TEXT,province TEXT,year INTEGER,month INTEGER,day INTEGER,password TEXT)");


        //create table orders
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

        //create order table
        // MyDB.execSQL(("create table orders(id INTEGER primary key AUTOINCREMENT,workemail TEXT,requirement TEXT,resource TEXT,subtotal INTEGER,servicecharge INTEGER,total INTEGER,buyer TEXT,seller TEXT,gigid INTEGER)"));


        MyDB.execSQL("create table gigs(id integer primary key autoincrement," +
                "title TEXT,category TEXT,description TEXT,deliver TEXT,advanceAmount TEXT," +
                "secondPayment TEXT,contact TEXT,image TEXT,timestamp TEXT)");

        localDatabase = MyDB;

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");


        //drop table query orders
        String DROP_TABLE_ORDERS = "DROP TABLE IF EXISTS " +TABLE_NAME_ORDER;

        //exe query
        MyDB.execSQL(DROP_TABLE_ORDERS);

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

    public void saveGig(String title, String category, String description, String delivery, String advanceAmount, String secondPayment,
                        String contact, String imageUrl) {

        if (localDatabase == null) {
            localDatabase = this.getWritableDatabase();
        }

        localDatabase.execSQL("insert into gigs(title,category,description ,deliver ,advanceAmount , secondPayment ,contact,image,timestamp ) " +
                "values('" + title + "','" + category + "','" + description + "','" + delivery + "','" + advanceAmount + "','" +
                secondPayment + "','" + contact + "','" + imageUrl + "','" + System.currentTimeMillis() + "')");


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

            GigHolder holder = new GigHolder(id, title, category, description, delivery, advanceAmount, secondPayment, contact, Uri.parse(image), time);
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

            GigHolder holder = new GigHolder(id, title, category, description, delivery, advanceAmount, secondPayment, contact, Uri.parse(image), time);
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
