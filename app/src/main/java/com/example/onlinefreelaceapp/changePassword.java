package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.passwordChangeSuccess;
import com.example.onlinefreelaceapp.DataBase.DBHelper;

public class changePassword extends AppCompatActivity {


    EditText oldPass, newPass, renewPass;
    Button changebtn;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        renewPass = (EditText) findViewById(R.id.renewPass);
        changebtn = (Button) findViewById(R.id.changebtn);

        DB = new DBHelper(this);

        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = oldPass.getText().toString();
                String newPassword = newPass.getText().toString();
                String renewPassword = renewPass.getText().toString();



                Boolean checkoldPass = DB.checkUpdatePassword(password);
                if (checkoldPass==true){
                    if(newPassword.equals(renewPassword)) {

                        Boolean checkpassUpdate = DB.updatePassword(newPassword);
                        if (checkpassUpdate == true) {
                            Intent intent = new Intent(getApplicationContext(), passwordChangeSuccess.class);
                            startActivity(intent);
                            Toast.makeText(changePassword.this, "Password Successfully Changed", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(changePassword.this, "Password Not Successfully Changed", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(changePassword.this, "Password Not Matching", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(changePassword.this,"Old password not same",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}