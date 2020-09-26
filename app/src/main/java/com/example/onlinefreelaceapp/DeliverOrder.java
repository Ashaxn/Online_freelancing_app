package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeliverOrder extends AppCompatActivity {

    TextView deliverOrderId,txtDeliverOrderDetails;
    Button btnOrderResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_order);

        deliverOrderId = (TextView) findViewById(R.id.deliverOrderId);
        txtDeliverOrderDetails = (TextView) findViewById(R.id.txtDeliverOrderDetails);
        btnOrderResource = (Button) findViewById(R.id.btnOrderResource);

        final String resLink = getIntent().getStringExtra("resource");

        btnOrderResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(resLink.equals("")) {
                    Toast.makeText(DeliverOrder.this,"Resource Not Available",Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(resLink));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.setPackage("com.android.chrome");
                  //  try {
                      //  startActivity(intent);
                    //} catch (ActivityNotFoundException e) {
                      //  intent.setPackage(null);
                        startActivity(intent);
                    //}
                }
            }
        });

        txtDeliverOrderDetails.setText(getIntent().getStringExtra("req"));
        deliverOrderId.setText("Order ID: #"+getIntent().getStringExtra("id"));

    }
}