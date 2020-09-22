package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.RequestPostUserView;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.UserDashboard;

public class Login extends AppCompatActivity {

    EditText usernameLogin,passwordLogin;
    Button btnLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        usernameLogin = (EditText) findViewById(R.id.loginUserName);
        passwordLogin = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameLogin.getText().toString();
                String pass = passwordLogin.getText().toString();

                if(username.equals("")||pass.equals("")) {
                    Toast.makeText(Login.this,"Please Enter Required Fields",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean check = DB.checkUsernamePassword(username,pass);
                    if(check==true) {
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void postRequestUserView(View view){
        startActivity(new Intent(getApplicationContext(), RequestPostUserView.class));
    }


}