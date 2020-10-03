package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class OrdersToBeRecHome extends AppCompatActivity {

    TextView ProcessingCount;
    DBHelper DB;

    CardView processingOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders__to_be_rec__home);

        ProcessingCount = (TextView) findViewById(R.id.OrdersToBeRecProcessingCount);

        processingOrders = (CardView) findViewById(R.id.homeOrdersToBeRecProcessing);

        DB = new DBHelper(this);

        int count = DB.getCountOrdersToBeRecProcessing(getIntent().getStringExtra("username"));

        if(count>0) {
            ProcessingCount.setText("You have "+count+" Orders");
        }else {
            ProcessingCount.setText("No Orders");
        }

        processingOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OrdersToBeReceivedProcessing.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}