package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

public class PostRequestUpdatePage extends AppCompatActivity {

    private EditText postTitle, postMobile, postDescription, postBudget, postCategory;
    private DatePicker postDatePicker;
    private Button updatePostBtn,updatePostCancelBtn;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_request_update_page);

        context = this;
        dbHelper = new DBHelper(context);

        postTitle = findViewById(R.id.update_postTitle);
        postMobile = findViewById(R.id.update_postMobile);
        postCategory = findViewById(R.id.update_postCategory);
        postDatePicker = (DatePicker) findViewById(R.id.update_postDatePicker);
        postDescription = findViewById(R.id.update_postDescription);
        postBudget = findViewById(R.id.update_postBudget);

        updatePostBtn = (Button) findViewById(R.id.update_postBtn);
        updatePostCancelBtn = (Button) findViewById(R.id.update_postCancelBtn);

        final String id = getIntent().getStringExtra("id");
        final PostRequestModel postrequestmodel = dbHelper.getSinglePost(Integer.parseInt(id));

        postTitle.setText(postrequestmodel.getPosttitle());
        postMobile.setText(postrequestmodel.getPostmobile());
        postCategory.setText(postrequestmodel.getPostcategory());
        postDatePicker.updateDate(postrequestmodel.getPostyear(),postrequestmodel.getPostmonth(),postrequestmodel.getPostday());
        postDescription.setText(postrequestmodel.getPostdesc());
        postBudget.setText(postrequestmodel.getPostbudget());

        updatePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postTitleText = postTitle.getText().toString();
                String postMobileText = postMobile.getText().toString();
                String postCategoryText = postCategory.getText().toString();
                int postyearText = postDatePicker.getYear();
                int postmonthText = postDatePicker.getMonth();
                int postdayText = postDatePicker.getDayOfMonth();
                String postDescriptionText = postDescription.getText().toString();
                String postBudgetText = postBudget.getText().toString();

                PostRequestModel postRequestModel = new PostRequestModel(Integer.parseInt(id),postTitleText,postMobileText,postCategoryText,postyearText,postmonthText,postdayText,postDescriptionText,postBudgetText);
                dbHelper.updateSinglePost(postRequestModel);
                startActivity(new Intent(context,PostARequestHome.class));
            }
        });


    }
}