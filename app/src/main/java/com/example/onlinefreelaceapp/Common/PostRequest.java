package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.onlinefreelaceapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class PostRequest extends AppCompatActivity {

    private TextInputLayout textInputLayout;

    private AutoCompleteTextView dropdown_autocomplete;

    EditText posttitle,postmobile;
    DatePicker postrequestDate;
    Button postrequestnext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_request);


        postmobile = (EditText) findViewById(R.id.txtPostMobile);
        posttitle = (EditText) findViewById(R.id.txtPostTitle);
        postrequestDate = (DatePicker) findViewById(R.id.txtPostDOB);
        textInputLayout = findViewById(R.id.post_request_categories);

        postrequestnext = (Button) findViewById(R.id.btnNextPostRequest);

        dropdown_autocomplete = findViewById(R.id.dropdown_menu_autocomplete);

        final String[] items = new String[]{
                "Graphic & Design",
                "Programming",
                "Digital Marketing",
                "Writing"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PostRequest.this,
                R.layout.post_request_dropdown_items,
                items

        );

        dropdown_autocomplete.setAdapter(adapter);

        postrequestnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String postmobi = postmobile.getText().toString();
                String posttit = posttitle.getText().toString();
                String postcat = dropdown_autocomplete.getText().toString();
                int postyear = postrequestDate.getYear();
                int postmonth = postrequestDate.getMonth();
                int postday = postrequestDate.getDayOfMonth();


                Intent intent = new Intent(getApplicationContext(), PostRequestPageTwo.class);
                intent.putExtra("postmobile",postmobi);
                intent.putExtra("posttitle",posttit);
                intent.putExtra("postcategory",postcat);
                intent.putExtra("postyear",postyear);
                intent.putExtra("postmonth",postmonth);
                intent.putExtra("postday",postday);
                startActivity(intent);
            }
        });

    }

    public void postRequestPageTwo(View view){

        startActivity(new Intent(getApplicationContext(), PostRequestPageTwo.class));
    }


}