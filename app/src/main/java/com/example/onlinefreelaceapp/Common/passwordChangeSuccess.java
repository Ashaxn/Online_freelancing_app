package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onlinefreelaceapp.Common.LoginSignup.Login;
import com.example.onlinefreelaceapp.R;

public class passwordChangeSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change_success);
    }

    public void changpasslogoutPage(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}