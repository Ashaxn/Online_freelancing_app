package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onlinefreelaceapp.Common.LoginSignup.Login;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.UserDashboard;
import com.example.onlinefreelaceapp.changePassword;

public class contactusSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_success);
    }

    public void backhome(View view) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("password");
        Intent intent1 = new Intent(getApplicationContext(), UserDashboard.class);
        intent1.putExtra("username",username);
        intent1.putExtra("password",pass);
        startActivity(intent1);
    }
}