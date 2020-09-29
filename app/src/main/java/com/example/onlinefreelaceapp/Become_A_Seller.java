package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Become_A_Seller extends AppCompatActivity {
    ImageButton imgbtn;
    Button shmrbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become__a__seller);

        imgbtn=findViewById(R.id.creategigbutton);
         shmrbtn=findViewById(R.id.graphicshowmorebtn);}

        @Override
        protected void onResume(){
            super.onResume();

            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Become_A_Seller.this,Create_A_Gig.class);
                    startActivity(intent);


                }
            });


            shmrbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Intent intent2 = new Intent(Become_A_Seller.this,View_Gig_For_Admin.class);
                    startActivity(intent2);
                }
            });
        }



    }
