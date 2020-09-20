package com.example.onlinefreelaceapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.onlinefreelaceapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class PostRequest extends AppCompatActivity {

    private TextInputLayout textInputLayout;

    private AutoCompleteTextView dropdown_autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_request);

        textInputLayout = findViewById(R.id.post_request_categories);
        dropdown_autocomplete = findViewById(R.id.dropdown_menu_autocomplete);

        String[] items = new String[]{
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

    }

    public void postRequestPageTwo(View view){

        startActivity(new Intent(getApplicationContext(), PostRequestPageTwo.class));
    }


}