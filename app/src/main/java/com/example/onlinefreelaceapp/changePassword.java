package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.LoginSignup.UsersModel;
import com.example.onlinefreelaceapp.Common.passwordChangeSuccess;
import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class changePassword extends AppCompatActivity {


    private EditText oldPass, newPass, renewPass;
    private Button changebtn;
    private DBHelper DB;
    private Context context;
    private UsersModel usersModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_password);

        oldPass =  findViewById(R.id.oldPass);
        newPass =  findViewById(R.id.newPass);
        renewPass =  findViewById(R.id.renewPass);
        changebtn = (Button) findViewById(R.id.changebtn);

        context = this;
        DB = new DBHelper(this);

        final String username = getIntent().getStringExtra("username");
        final String pass = getIntent().getStringExtra("password");


        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String oldPassword = oldPass.getText().toString();
                String newPassword = newPass.getText().toString();
                String renewPassword = renewPass.getText().toString();



                if (oldPassword.equals(pass)){
                    if(newPassword.equals(renewPassword)) {
                            UsersModel usersModel = new UsersModel (username, newPassword);
                            DB.updatePassword(usersModel);
                            Intent intent = new Intent(getApplicationContext(), passwordChangeSuccess.class);
                            startActivity(intent);
                            Toast.makeText(changePassword.this, "Password Successfully Changed", Toast.LENGTH_LONG).show();
                        }
                        else {

                            Toast.makeText(changePassword.this, "Password Not Successfully Changed", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(changePassword.this, "Old Password Not Matching", Toast.LENGTH_LONG).show();
                    }

                }


        });
    }
}