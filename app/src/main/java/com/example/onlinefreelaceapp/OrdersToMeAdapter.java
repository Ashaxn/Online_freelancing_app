package com.example.onlinefreelaceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrdersToMeAdapter extends ArrayAdapter<Orders> {

    private Context context;
    private int resource;
    private List<Orders> ordersList;

    OrdersToMeAdapter(Context context, int resource, List<Orders> ordersList) {
        super(context,resource,ordersList);
        this.context = context;
        this.resource = resource;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View row = inflater.inflate(resource,parent,false);

        TextView RecOrderId = (TextView) row.findViewById(R.id.OrdersToMeProcessingOrderIdItem);
        TextView buyerId = (TextView) row.findViewById(R.id.OrdersToMeProcessingBuyer);
        TextView RecOrderEmail = (TextView) row.findViewById(R.id.ordersToMeProcessingEmail);
        TextView RecOrderSubmitDate = (TextView) row.findViewById(R.id.OrdersToMeListSubmitDate);
        TextView RecOrderPrice = (TextView) row.findViewById(R.id.ordersToMeListPrice);

        Orders order = ordersList.get(position);
        RecOrderId.setText(Integer.toString(order.getId()));
        buyerId.setText("Order By: "+order.getBuyer());
        RecOrderEmail.setText(order.getWork_email());

        return row;
    }

}
