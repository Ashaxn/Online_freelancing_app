package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.AccountCreateSuccessfully;
import com.example.onlinefreelaceapp.Common.LoginSignup.SignUp;

import com.example.onlinefreelaceapp.Common.LoginSignup.SignUpPageTwo;
import com.example.onlinefreelaceapp.Common.contactusSuccess;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class contactus extends AppCompatActivity {

    EditText name,description;
    Button consubmit;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contactus);

            name = (EditText) findViewById(R.id.conName);
            description = (EditText) findViewById(R.id.conDesc);

            consubmit = (Button) findViewById(R.id.Consubmitbtn);

            DB = new DBHelper(this);

            consubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String cname = name.getText().toString();
                    String cdes = description.getText().toString();

                    Boolean insert1 = DB.insertContactUs(cname,cdes);
                    if(insert1==false) {
                        Toast.makeText(contactus.this,"Sign Up Not Success!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(contactus.this,"Sign Up Successful!",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), contactusSuccess.class);
                        startActivity(intent1);
                    }


                }
            });
    }



}