package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.onlinefreelaceapp.Become_A_Seller;
import com.example.onlinefreelaceapp.Common.RequestPostUserView;
import com.example.onlinefreelaceapp.R;

public class Login extends AppCompatActivity {

    Button tempbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        tempbt=findViewById(R.id.temporybtn);
    }

    public void postRequestUserView(View view){
        startActivity(new Intent(getApplicationContext(), RequestPostUserView.class));
    }


    @Override
    protected void onResume() {
        super.onResume();

        tempbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Become_A_Seller.class);
                startActivity(intent);

            }
        });
    }


}