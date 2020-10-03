package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class UpdateOrder extends AppCompatActivity {

    EditText email,resource,req;
    TextView service,total,subtotal;
    Button updateBtn;
    Context context;
    private long updateDate;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);

        context = this;

        email = (EditText) findViewById(R.id.txtWorkingEmailUpdateOrder);
        resource = (EditText) findViewById(R.id.linkUpdateOrderResource);
        req = (EditText) findViewById(R.id.txtUpdateOrderRequirement);
        subtotal = (TextView) findViewById(R.id.txtOrderSubTotalUpdate);
        service = (TextView) findViewById(R.id.txtOrderServiceChargeUpdate);
        total = (TextView) findViewById(R.id.txtOrderTotalUpdate);

        DB = new DBHelper(context);

        updateBtn = (Button) findViewById(R.id.btnUpdateOrder);

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);
        Orders order = DB.getSingleOrderToBeRec(Integer.parseInt(id));

        email.setText(order.getWork_email());
        req.setText(order.getReq());
        resource.setText(order.getResource());
        subtotal.setText(Integer.toString(order.getSubTot()));
        service.setText(Integer.toString(order.getServiceCharge()));
        total.setText(Integer.toString(order.getTotal()));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String resource1 = resource.getText().toString();
                String req1 = req.getText().toString();

                int result = DB.updateSingleOrdersToBeRecProcessing(Integer.parseInt(id),email1,resource1,req1);


                System.out.println(result);



                Intent intent = new Intent(getApplicationContext(),OrdersToBeReceivedProcessing.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });




    }
}