package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.onlinefreelaceapp.Common.PostRequestModel;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.settings;

public class updateUser extends AppCompatActivity {

    private EditText profFull, profEmail, profProv, profMobile;
    private DatePicker profDatePicker;
    private Button updateProfBtn, updateProfCancelBtn;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

       context = this;
        dbHelper = new DBHelper(context);

        profFull = findViewById(R.id.update_profFull);
        profEmail = findViewById(R.id.update_profEmail);
        profProv = findViewById(R.id.update_profProv);
        profDatePicker = findViewById(R.id.update_profDatePicker);
        profMobile = findViewById(R.id.update_profMobile);

        updateProfBtn = (Button) findViewById(R.id.update_profBtn);

        final String username = getIntent().getStringExtra("username");
        final String pass = getIntent().getStringExtra("password");
        final UsersModel usersModel = dbHelper.getProfileView(username);

        profFull.setText(usersModel.getFullname());
        profEmail.setText(usersModel.getEmail());
        profProv.setText(usersModel.getProvince());
        profDatePicker.updateDate(usersModel.getYear(),usersModel.getMonth(),usersModel.getDay());
        profMobile.setText(usersModel.getMobilenumber());

        updateProfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profFullText = profFull.getText().toString();
                String profEmailText = profEmail.getText().toString();
                String profProvText = profProv.getText().toString();
                int profyearText = profDatePicker.getYear();
                int profmonthText = profDatePicker.getMonth();
                int profdayText = profDatePicker.getDayOfMonth();
                String profMobileText = profMobile.getText().toString();

                UsersModel usersModel = new UsersModel(username, profFullText,profEmailText,profProvText,profyearText,profmonthText,profdayText,profMobileText);
                dbHelper.updateProfile(usersModel);
                Intent intent1 = new Intent(getApplicationContext(), settings.class);
                intent1.putExtra("username",username);
                intent1.putExtra("password",pass);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                //startActivity(new Intent(context, settings.class));
            }
        });
    }

}