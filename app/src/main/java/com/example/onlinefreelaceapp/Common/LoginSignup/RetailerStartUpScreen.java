
package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.changePassword;

public class RetailerStartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_up_screen);
    }

    public void callLoginScreen(View view){
        startActivity(new Intent(getApplicationContext(),Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void callSignUpScreen(View view){
        startActivity(new Intent(getApplicationContext(),SignUp.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void callcontus(View view){
        startActivity(new Intent(getApplicationContext(), changePassword.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}