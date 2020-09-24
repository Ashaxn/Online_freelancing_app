package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class PostRequestPageTwo extends AppCompatActivity {

    EditText postdesc,postbudget;
    Button submitpost;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_request_page_two);

        postdesc = (EditText) findViewById(R.id.txtPostDesc);
        postbudget = (EditText) findViewById(R.id.txtPostBudget);
        submitpost = (Button) findViewById(R.id.btnSubmitPostRequest);

        DB = new DBHelper(this);

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

                if(postmobile.equals("")||posttitle.equals("")||postcategory.equals("")||postdescription.equals("")||postbud.equals("")) {
                    Toast.makeText(PostRequestPageTwo.this,"Required All Fields!",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkPostRequests = DB.checkPostRequest(postmobile);
                    if(checkPostRequests==false) {
                        Boolean insert = DB.insertPostRequest(postmobile, posttitle, postcategory, postyear, postmonth, postday, postdescription, postbud);
                        if(insert==false) {
                            Toast.makeText(PostRequestPageTwo.this,"Request Post Not SUccess",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(PostRequestPageTwo.this,"Post Requested Successful!",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(),AccountCreateSuccessfully.class);
                            startActivity(intent1);
                        }
                    }else {
                        Toast.makeText(PostRequestPageTwo.this,"Something Happened!!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}