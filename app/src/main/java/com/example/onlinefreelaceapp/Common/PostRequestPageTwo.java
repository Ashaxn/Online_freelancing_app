package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class PostRequestPageTwo extends AppCompatActivity {

    EditText postdesc,postbudget;
    Button submitpost;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_request_page_two);

        postdesc = (EditText) findViewById(R.id.txtPostDesc);
        postbudget = (EditText) findViewById(R.id.txtPostBudget);
        submitpost = (Button) findViewById(R.id.btnSubmitPostRequest);
        context = this;

        dbHelper = new DBHelper(context);

        submitpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postdescription = postdesc.getText().toString();
                String postbud = postbudget.getText().toString();

                Intent intent = getIntent();
                String postmobile = intent.getStringExtra("postmobile");
                String posttitle = intent.getStringExtra("posttitle");
                String postcategory = intent.getStringExtra("postcategory");
                int postyear = intent.getIntExtra("postyear",0);
                int postmonth = intent.getIntExtra("postmonth",0);
                int postday = intent.getIntExtra("postday",0);

                String username = getIntent().getStringExtra("username");

                PostRequestModel postRequestModel = new PostRequestModel(postyear,postmonth,postday,posttitle,postmobile,postcategory,postdescription,postbud,username);
                dbHelper.insertPostRequest(postRequestModel);

                Toast.makeText(context,"Successfully Added",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),PostARequestHome.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
    }
}