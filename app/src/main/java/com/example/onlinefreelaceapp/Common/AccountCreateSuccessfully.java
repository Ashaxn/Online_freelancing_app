package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.Common.LoginSignup.Login;

public class AccountCreateSuccessfully extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create_successfully);

    }

    public void loginPage(View view) {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

}