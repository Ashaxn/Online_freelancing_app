package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlinefreelaceapp.R;

public class SignUp extends AppCompatActivity {

    EditText fullname,email,username,province,mobilenumber;
    Button signupnext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.txtUsername);
        fullname = (EditText) findViewById(R.id.txtFullname);
        email = (EditText) findViewById(R.id.txtEmail);
        province = (EditText) findViewById(R.id.txtProvince);
        mobilenumber = (EditText) findViewById(R.id.txtMobileNumber);

        signupnext = (Button) findViewById(R.id.btnNextSignUp);

        signupnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usern = username.getText().toString();
                String fulln = fullname.getText().toString();
                String email1 = email.getText().toString();
                String province1 = province.getText().toString();
                String mob = mobilenumber.getText().toString();


                Intent intent = new Intent(getApplicationContext(),SignUpPageTwo.class);
                intent.putExtra("username",usern);
                intent.putExtra("fullname",fulln);
                intent.putExtra("email",email1);
                intent.putExtra("province",province1);
                intent.putExtra("mobilenumber",mob);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    public void callLoginScreen(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

}