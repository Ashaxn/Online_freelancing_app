package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlinefreelaceapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PostARequestHome extends AppCompatActivity {

    FloatingActionButton postrequest_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_a_request_home);

        postrequest_btn = findViewById(R.id.postrequest_btn);
        postrequest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostARequestHome.this, PostRequest.class);
                startActivity(intent);
            }
        });

        }



}