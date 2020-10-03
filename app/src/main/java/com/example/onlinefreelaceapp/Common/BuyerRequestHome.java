package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

import java.util.ArrayList;
import java.util.List;

public class BuyerRequestHome extends AppCompatActivity {

    private ListView requestlistView;
    private DBHelper dbHelper;
    private List<PostRequestModel> buyerrequestPosts;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_buyer_request_home);

        context = this;
        dbHelper = new DBHelper(context);
        requestlistView = findViewById(R.id.requestlist);
        buyerrequestPosts = new ArrayList<>();

        buyerrequestPosts = dbHelper.getAllRequests();
        BuyerRequestAdapter adapter = new BuyerRequestAdapter(context, R.layout.buyer_request_card, buyerrequestPosts);
        requestlistView.setAdapter(adapter);

        requestlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final PostRequestModel postrequestmodel = buyerrequestPosts.get(position);

                Intent intent = new Intent(view.getContext(), BuyerRequestUser.class);
                intent.putExtra("id",String.valueOf(postrequestmodel.getId()));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });

    }



}
