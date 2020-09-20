package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlinefreelaceapp.R;

public class RequestPostUserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_request_post_user_view);
    }
    public void sendOfferSecond(View view){

        startActivity(new Intent(getApplicationContext(), BuyerRequestSendOffer.class));
    }

}