package com.example.onlinefreelaceapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.Common.AccountCreateSuccessfully;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class SignUpPageTwo extends AppCompatActivity {

    EditText password,repassword;
    Button signup;
    DatePicker dob;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_page_two);


        dob = (DatePicker) findViewById(R.id.txtDob);
        password = (EditText) findViewById(R.id.txtPassword);
        repassword = (EditText) findViewById(R.id.txtRePassword);
        signup = (Button) findViewById(R.id.btnSignUp);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year = dob.getYear();
                int month = dob.getMonth();
                int day = dob.getDayOfMonth();
                String password1 = password.getText().toString();
                String repassword1 = repassword.getText().toString();
                Intent intent = getIntent();

                String username = intent.getStringExtra("username");
                String fullname = intent.getStringExtra("fullname");
                String email = intent.getStringExtra("email");
                String province = intent.getStringExtra("province");
                String mobilenumber = intent.getStringExtra("mobilenumber");

                if(username.equals("")||email.equals("")||fullname.equals("")||province.equals("")||mobilenumber.equals("")||password1.equals("")||repassword1.equals("")) {
                    Toast.makeText(SignUpPageTwo.this,"Required All Fields!",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuser = DB.checkUser(username);
                    if(checkuser==false) {
                        if(password1.equals(repassword1)) {

                            UsersModel usersModel= new UsersModel(year,month,day,username,fullname,email,mobilenumber,province,password1);
                            boolean insert= DB.insertUsers(usersModel);


                                if(insert==false) {
                                Toast.makeText(SignUpPageTwo.this,"Sign Up Not Success!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SignUpPageTwo.this,"Sign Up Successful!",Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(getApplicationContext(), AccountCreateSuccessfully.class);
                                startActivity(intent1);
                            }
                        }else {
                            Toast.makeText(SignUpPageTwo.this,"Please Enter Match password!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }

    public void callSignUpScreenOne(View view){
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }



}