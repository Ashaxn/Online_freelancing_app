package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlinefreelaceapp.R;

public class PostARequestHome extends AppCompatActivity {

    private Button postadd;
    private ListView listView;
    private TextView postcount;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_a_request_home);

        postadd = findViewById(R.id.postadd);
        listView = findViewById(R.id.postlist);
        postcount = findViewById(R.id.postcount);
        context = this;

        postadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PostRequest.class));
            }
        });

        }

}