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

public class OrderAdapter extends ArrayAdapter<Orders> {

    private Context context;
    private int resource;
    private List<Orders> ordersList;

    OrderAdapter(Context context, int resource, List<Orders> ordersList) {
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

        TextView RecOrderId = (TextView) row.findViewById(R.id.OrdersToBeRecProcessingOrderId);
        TextView sellerId = (TextView) row.findViewById(R.id.OrdersToBeRecProcessingSeller);
        TextView RecOrderEmail = (TextView) row.findViewById(R.id.ordersToBeRecProcessingEmail);
        TextView RecOrderSubmitDate = (TextView) row.findViewById(R.id.OrdersToBeRecListSubmitDate);
        TextView RecOrderPrice = (TextView) row.findViewById(R.id.ordersToBeRecListPrice);
        ImageView RecOrderCheck = (ImageView) row.findViewById(R.id.ordersToBeRecCheck);

        Orders order = ordersList.get(position);
        RecOrderId.setText(Integer.toString(order.getId()));
        sellerId.setText("To: "+order.getSeller());
        RecOrderEmail.setText(order.getWork_email());
        RecOrderPrice.setText("$"+Integer.toString(order.getTotal()));

        if(order.getFinishdate()>0) {
            RecOrderCheck.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
