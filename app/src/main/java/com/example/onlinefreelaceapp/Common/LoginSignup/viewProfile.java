package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class viewProfile extends AppCompatActivity {

    private TextView viewprofFull, viewprofEmail, viewprofProv, viewprofMobile, viewprofDate;
    private DBHelper MyDB;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_profile);



        context = this;
        MyDB = new DBHelper(context);

        viewprofFull = findViewById(R.id.viewprof_full);
        viewprofEmail = findViewById(R.id.viewprof_email);
        viewprofProv = findViewById(R.id.viewprof_prov);
        viewprofMobile = findViewById(R.id.viewprof_mobile);
        viewprofDate = findViewById(R.id.viewprof_dob);



        final String username = getIntent().getStringExtra("username");
        final UsersModel usersModel = MyDB.getProfileView(username);

        viewprofFull.setText(""+usersModel.getFullname()+"");
        viewprofEmail.setText(""+usersModel.getEmail()+"");
        viewprofProv.setText(""+usersModel.getProvince()+"");
        viewprofMobile.setText(""+usersModel.getMobilenumber()+"");
        viewprofDate.setText(""+usersModel.getYear()+","+usersModel.getMonth()+","+usersModel.getDay()+"");
    }



}