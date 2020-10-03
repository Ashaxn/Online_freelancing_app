package com.example.onlinefreelaceapp.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PostARequestHome extends AppCompatActivity {

    private FloatingActionButton postadd;
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

        requestPosts = new ArrayList<>();

        requestPosts = dbHelper.getAllPosts(getIntent().getStringExtra("username"));
        PostRequestAdapter adapter = new PostRequestAdapter(context,R.layout.postrequest_card,requestPosts);
        listView.setAdapter(adapter);

        //Get Post Count From the Table
        //int countPost = dbHelper.postCount();
        //postcount.setText(""+countPost+" Requests");

        postadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PostRequest.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(intent);
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
                        intent.putExtra("username",getIntent().getStringExtra("username"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,PostARequestHome.class);
                        dbHelper.deletePostRequest(postrequestmodel.getId());
                        intent.putExtra("username",getIntent().getStringExtra("username"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,PostRequestUpdatePage.class);
                        intent.putExtra("id",String.valueOf(postrequestmodel.getId()));
                        intent.putExtra("username",getIntent().getStringExtra("username"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}