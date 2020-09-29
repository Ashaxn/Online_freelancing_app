package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AddPayment extends AppCompatActivity {

    TextView amount;
    EditText cardNumber,cardName,expireDate,cvcNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);





    }
}