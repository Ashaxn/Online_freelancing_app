package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class OrdersToMeHome extends AppCompatActivity {

    CardView processingOrder;
    TextView ProcessingCount;
    ImageView back;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_to_me_home);

        processingOrder = (CardView) findViewById(R.id.homeOrdersToMeProcessing);
        back = (ImageView) findViewById(R.id.btnBackOrdersToMeHome);

        ProcessingCount = (TextView) findViewById(R.id.OrdersToMeProcessingCount);
        DB = new DBHelper(this);

        int count = DB.getCountOrdersToMeProcessing(getIntent().getStringExtra("username"));

        if(count>0) {
            ProcessingCount.setText("You have "+count+" Orders");
        }else {
            ProcessingCount.setText("No Orders");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        processingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OrdersToMeProcessing.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}