package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.AccountCreateSuccessfully;
import com.example.onlinefreelaceapp.Common.LoginSignup.Login;
import com.example.onlinefreelaceapp.Common.LoginSignup.RetailerStartUpScreen;
import com.example.onlinefreelaceapp.Common.LoginSignup.SignUpPageTwo;
import com.example.onlinefreelaceapp.Common.LoginSignup.UsersModel;
import com.example.onlinefreelaceapp.Common.LoginSignup.updateUser;
import com.example.onlinefreelaceapp.Common.LoginSignup.viewProfile;
import com.example.onlinefreelaceapp.Common.PostARequestHome;
import com.example.onlinefreelaceapp.Common.PostRequestUpdatePage;
import com.example.onlinefreelaceapp.Common.RequestPostUserView;
import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class settings extends AppCompatActivity {

    private Button viewbutton,deactivate,update,changepass;
    private DBHelper Mydb;
    private Context context;
    UsersModel usersModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);


        context = this;
        Mydb = new DBHelper(context);
        viewbutton = (Button) findViewById(R.id.viewbtn);
        deactivate = (Button) findViewById(R.id.user_deact);
        update = (Button) findViewById(R.id.updatebutton);
        changepass= (Button) findViewById(R.id.changepass);

//view button
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                String pass = intent.getStringExtra("password");
                Intent intent1 = new Intent(context, viewProfile.class);
                intent1.putExtra("username",String.valueOf(username));
                intent1.putExtra("password",pass);
                startActivity(intent1);
            }
        });
//deactivate button
        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                Mydb.deleteProfile(username);
                startActivity(new Intent(context,Login.class));
                }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                String pass = intent.getStringExtra("password");
                Intent intent1 = new Intent(context, updateUser.class);
                intent1.putExtra("username",String.valueOf(username));
                intent1.putExtra("password",pass);
                startActivity(intent1);
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                String pass = intent.getStringExtra("password");
                Intent intent1 = new Intent(getApplicationContext(), changePassword.class);
                intent1.putExtra("username",username);
                intent1.putExtra("password",pass);
                startActivity(intent1);

            }
        });

    }



    public void callLogout(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));


    }


    public void contactus(View view) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("password");
        Intent intent1 = new Intent(getApplicationContext(), contactus.class);
        intent1.putExtra("username",username);
        intent1.putExtra("password",pass);
        startActivity(intent1);
    }

    public void aboutuspage(View view) {
        startActivity(new Intent(getApplicationContext(), aboutus.class));
    }
}