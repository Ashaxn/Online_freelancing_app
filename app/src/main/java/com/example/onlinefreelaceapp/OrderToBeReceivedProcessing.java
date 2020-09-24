package com.example.onlinefreelaceapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderToBeReceivedProcessing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderToBeReceivedProcessing extends Fragment {

    View view;
    DBHelper DB;
    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderToBeReceivedProcessing() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderToBeReceivedProcessing.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderToBeReceivedProcessing newInstance(String param1, String param2) {
        OrderToBeReceivedProcessing fragment = new OrderToBeReceivedProcessing();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_order_to_be_received_processing, container, false);

        context = container.getContext();

        Intent intent = getActivity().getIntent();

        String usernameIntent = intent.getStringExtra("username");


        Toast.makeText(getActivity().getApplicationContext(),usernameIntent,Toast.LENGTH_SHORT).show();

        DB = new DBHelper(getActivity());

        ArrayList<HashMap<String,String>> orderList = DB.getBuyerMyOrderList(usernameIntent);

        ListView lv = (ListView) view.findViewById(R.id.listViewOrdersToBeRecProcessing);

        ListAdapter adapter = new SimpleAdapter(getActivity(),orderList,R.layout.orderstoberecieved_processing_listitem,new String[]{"orderid","email","total","sellerid"},new int[]{R.id.OrdersToBeRecProcessingOrderId,R.id.ordersToBeRecProcessingEmail,R.id.ordersToBeRecListPrice,R.id.OrdersToBeRecProcessingBuyer});

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Test");
                builder.setMessage("Massage");
                builder.show();
            }
        });



        return view;
    }
}