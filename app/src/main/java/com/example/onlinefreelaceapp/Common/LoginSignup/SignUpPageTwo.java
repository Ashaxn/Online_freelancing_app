package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlinefreelaceapp.Common.AccountCreateSuccessfully;
import com.example.onlinefreelaceapp.R;

public class SignUpPageTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_page_two);
    }

    public void callSignUpScreenOne(View view){
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

    public void callRegistrationSuccess(View view){
        startActivity(new Intent(getApplicationContext(), AccountCreateSuccessfully.class));
    }

}