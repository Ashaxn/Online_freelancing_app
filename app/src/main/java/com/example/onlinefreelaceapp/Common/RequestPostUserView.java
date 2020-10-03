package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class RequestPostUserView extends AppCompatActivity {

    private TextView viewposttitle, viewpostmobile, viewpostdescription, viewpostbudget, viewpostcategory,viewpostdate;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_request_post_user_view);

        context = this;
        dbHelper = new DBHelper(context);

        viewposttitle = findViewById(R.id.viewpost_postTitle);
        viewpostmobile = findViewById(R.id.viewpost_postMobile);
        viewpostcategory = findViewById(R.id.viewpost_postCategory);
        viewpostdate = findViewById(R.id.viewpost_postDate);
        viewpostdescription = findViewById(R.id.viewpost_postDesc);
        viewpostbudget = findViewById(R.id.viewpost_postBudget);

        final String id = getIntent().getStringExtra("id");
        final PostRequestModel postrequestmodel = dbHelper.getSinglePost(Integer.parseInt(id));

        viewposttitle.setText(""+postrequestmodel.getPosttitle()+"");
        viewpostmobile.setText(""+postrequestmodel.getPostmobile()+"");
        viewpostcategory.setText(""+postrequestmodel.getPostcategory()+"");
        viewpostdate.setText(""+postrequestmodel.getPostyear()+"");
        viewpostdescription.setText(""+postrequestmodel.getPostdesc()+"");
        viewpostbudget.setText(""+postrequestmodel.getPostbudget()+"");

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}