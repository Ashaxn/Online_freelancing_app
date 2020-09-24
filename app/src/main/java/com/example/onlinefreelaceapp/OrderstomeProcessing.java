package com.example.onlinefreelaceapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderstomeProcessing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderstomeProcessing extends Fragment {


    DBHelper DB;

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderstomeProcessing() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderstomeProcessing.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderstomeProcessing newInstance(String param1, String param2) {
        OrderstomeProcessing fragment = new OrderstomeProcessing();
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

        view = inflater.inflate(R.layout.fragment_orderstome_processing, container, false);

        Intent intent = getActivity().getIntent();

        String usernameIntent = intent.getStringExtra("username");


        Toast.makeText(getActivity().getApplicationContext(),usernameIntent,Toast.LENGTH_SHORT).show();

        DB = new DBHelper(getActivity());

        ArrayList<HashMap<String,String>> orderList = DB.getOrderList(usernameIntent);

        ListView lv = (ListView) view.findViewById(R.id.listViewOrdersToMeProcessing);

        ListAdapter adapter = new SimpleAdapter(getActivity(),orderList,R.layout.orderstome_processing_listitem,new String[]{"orderid","email","total","buyerid"},new int[]{R.id.OrdersToMeProcessingOrderId,R.id.ordersToMeProcessingEmail,R.id.ordersToMeListPrice,R.id.OrdersToMeProcessingBuyer});

        lv.setAdapter(adapter);

        String[] countries = new  String[]{
                "Sri Lanka", "India", "USA", "UK"
        };



        return view;
    }
}