package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersToBeReceivedProcessing extends AppCompatActivity {

    private ListView listView;
    DBHelper DB;
    Context context;

    private List<Orders> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_to_be_received_processing);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        context = this;

        Toast.makeText(this,username,Toast.LENGTH_SHORT).show();

        DB = new DBHelper(this);
        listView =  findViewById(R.id.listViewOrdersToBeRecProcessing);
        ordersList = new ArrayList<>();

        ordersList = DB.getBuyerMyOrderList(username);

        OrderAdapter adapter = new OrderAdapter(context,R.layout.orderstoberecieved_processing_listitem,ordersList);
        listView.setAdapter(adapter);


        //ArrayList<HashMap<String,String>> orderList = DB.getBuyerMyOrderList(usernameintent);

       // ListView lv = (ListView) findViewById(R.id.listViewOrdersToBeRecProcessing);

        //ListAdapter adapter = new SimpleAdapter(this,orderList,R.layout.orderstoberecieved_processing_listitem,new String[]{"orderid","email","total","sellerid"},new int[]{R.id.OrdersToBeRecProcessingOrderId,R.id.ordersToBeRecProcessingEmail,R.id.ordersToBeRecListPrice,R.id.OrdersToBeRecProcessingBuyer});

        //lv.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final Orders order = ordersList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(Integer.toString(order.getId()));
                builder.setMessage(order.getReq());

                builder.setPositiveButton("Edit Order", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,UpdateOrder.class);
                        intent.putExtra("username",username);
                        intent.putExtra("id",String.valueOf(order.getId()));
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("Delete Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DB.deleteOrdersToBeRec(order.getId());
                        Intent intent = new Intent(context,OrdersToBeRecHome.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                });

                builder.show();

            }
        });

    }
}