package com.example.onlinefreelaceapp.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;

import java.util.ArrayList;
import java.util.List;

public class PostARequestHome extends AppCompatActivity {

    private Button postadd;
    private ListView listView;
    private TextView postcount;
    private DBHelper dbHelper;
    private List<PostRequestModel> requestPosts;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_a_request_home);

        context = this;
        dbHelper = new DBHelper(context);
        postadd = findViewById(R.id.postadd);
        listView = findViewById(R.id.postlist);
        postcount = findViewById(R.id.postcount);
        requestPosts = new ArrayList<>();

        requestPosts = dbHelper.getAllPosts();
        PostRequestAdapter adapter = new PostRequestAdapter(context,R.layout.postrequest_card,requestPosts);
        listView.setAdapter(adapter);

        //Get Post Count From the Table
        int countPost = dbHelper.postCount();
        postcount.setText(""+countPost+" Requests");

        postadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PostRequest.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final PostRequestModel postrequestmodel = requestPosts.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(postrequestmodel.getPosttitle());
                builder.setMessage(postrequestmodel.getPostcategory());
                builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,RequestPostUserView.class);
                        intent.putExtra("id",String.valueOf(postrequestmodel.getId()));
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deletePostRequest(postrequestmodel.getId());
                        startActivity(new Intent(context,PostARequestHome.class));
                    }
                });
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,PostRequestUpdatePage.class);
                        intent.putExtra("id",String.valueOf(postrequestmodel.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }

}