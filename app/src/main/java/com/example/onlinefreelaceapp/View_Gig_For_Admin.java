package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Gig_For_Admin extends AppCompatActivity {

    Button upbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__gig__for__admin);

        upbtn = findViewById(R.id.updatebutten_byadmin);


    }


    @Override
    protected void onResume() {
        super.onResume();

        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(View_Gig_For_Admin.this,Update_Gig_by_Admin.class);
                startActivity(intent3);


            }
        });
    }
}