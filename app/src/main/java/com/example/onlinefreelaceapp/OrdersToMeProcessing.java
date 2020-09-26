package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersToMeProcessing extends AppCompatActivity {

    DBHelper DB;
    ImageView btnBackOrdersToMeProcessing;
    ListView listView;

    Context context;

    private List<Orders> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_to_me_processing);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        context = this;

        Toast.makeText(this,username,Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.listViewOrdersToMeProcessing);



        DB = new DBHelper(this);

        orderList = new ArrayList<>();

        orderList = DB.getSellerMyOrderList(username);

        OrdersToMeAdapter adapter = new OrdersToMeAdapter(context,R.layout.orderstome_processing_listitem,orderList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Orders order = orderList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(Integer.toString(order.getId()));
                builder.setMessage(order.getReq());

                builder.setPositiveButton("Deliver Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context,DeliverOrder.class);
                        intent.putExtra("username",username);
                        intent.putExtra("id",String.valueOf(order.getId()));
                        intent.putExtra("resource",order.getResource());
                        intent.putExtra("req",order.getReq());
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("Delete Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DB.deleteOrdersToBeRec(order.getId());
                        Intent intent = new Intent(context,OrdersToMeHome.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                });

                builder.show();

            }
        });


       /* ArrayList<HashMap<String,String>> orderList = DB.getOrderList(usernameintent);

        lv = (ListView) findViewById(R.id.listViewOrdersToMeProcessing);

        ListAdapter adapter = new SimpleAdapter(this,orderList,R.layout.orderstome_processing_listitem,new String[]{"orderid","email","total","buyerid"},new int[]{R.id.OrdersToMeProcessingOrderId,R.id.ordersToMeProcessingEmail,R.id.ordersToMeListPrice,R.id.OrdersToMeProcessingBuyer});

        lv.setAdapter(adapter);

        btnBackOrdersToMeProcessing = (ImageView) findViewById(R.id.btnBackOrdersToMeProcessing);

        btnBackOrdersToMeProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OrdersToMeHome.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                String text = lv.getItemAtPosition(i).toString();

                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Test");
                builder.setMessage("Hello");
                builder.show();

            }
        });
*/




    }
}