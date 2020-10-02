package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class BuyerRequestUser extends AppCompatActivity {

    private TextView buyerposttitle, buyerpostmobile, buyerpostdescription, buyerpostbudget, buyerpostcategory,buyerpostdate;
    private DBHelper dbHelper;
    private Context context;
    private Button callButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_buyer_request_user);


        context = this;
        dbHelper = new DBHelper(context);

        buyerposttitle = findViewById(R.id.buyer_postTitle);
        buyerpostmobile = findViewById(R.id.buyer_postMobile);
        buyerpostcategory = findViewById(R.id.buyer_postCategory);
        buyerpostdate = findViewById(R.id.buyer_postDate);
        buyerpostdescription = findViewById(R.id.buyer_postDesc);
        buyerpostbudget = findViewById(R.id.buyer_postBudget);
        callButton = findViewById(R.id.callButton);

        final String id = getIntent().getStringExtra("id");
        final PostRequestModel buyerrequestmodel = dbHelper.getSinglePost(Integer.parseInt(id));

        buyerposttitle.setText(""+buyerrequestmodel.getPosttitle()+"");
        buyerpostmobile.setText(""+buyerrequestmodel.getPostmobile()+"");
        buyerpostcategory.setText(""+buyerrequestmodel.getPostcategory()+"");
        buyerpostdate.setText(""+buyerrequestmodel.getPostyear()+"");
        buyerpostdescription.setText(""+buyerrequestmodel.getPostdesc()+"");
        buyerpostbudget.setText(""+buyerrequestmodel.getPostbudget()+"");

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+buyerrequestmodel.getPostmobile()+""));
                startActivity(intent);
            }
        });

    }
}
