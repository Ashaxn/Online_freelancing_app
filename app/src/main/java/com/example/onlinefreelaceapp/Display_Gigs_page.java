package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.HelperClasses.UiRefresh;
import com.example.onlinefreelaceapp.adapter.GigAdapter;
import com.example.onlinefreelaceapp.adapter.GigHolder;
import com.example.onlinefreelaceapp.ui.main.ViewAsCustomerActivity;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Display_Gigs_page extends AppCompatActivity implements UiRefresh {

    FloatingActionButton fab;
    private DBHelper dbHelper;
    private GigAdapter adapter;
    private RecyclerView recyclerView;
    private List<GigHolder> list;
    private boolean isOnResume = false;
    private ShimmerFrameLayout shimmerFrameLayout;
    private LinearLayout emptyContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__gigs_page);


        dbHelper = new DBHelper(this);

        fab = findViewById(R.id.addgigbutton);
        recyclerView = findViewById(R.id.rv_gigs);
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        emptyContainer = findViewById(R.id.ll_empty);

        //init
        adapter = new GigAdapter(this, true,dbHelper,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Display_Gigs_page.this, Create_A_Gig.class);
                startActivity(intent1);

            }
        });

        findViewById(R.id.btn_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Display_Gigs_page.this, ViewAsCustomerActivity.class));
                finish();
            }
        });


        loadGigs();
    }


    private void loadGigs() {


        new AsyncTask<Void, Void, Void>() {


            @Override
            protected Void doInBackground(Void... voids) {

                list = dbHelper.getAllGigs();

                //added fake lag for show loading animation
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPreExecute() {

                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();

            }

            @Override
            protected void onPostExecute(Void aVoid) {

                adapter.clear();
                if (list != null && list.size() > 0) {
                    adapter.addAll(list);
                    emptyContainer.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    //show empty screen
                    emptyContainer.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmer();

            }
        }.execute();
    }

    @Override
    protected void onResume() {
        if (isOnResume) {
            loadGigs();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        isOnResume = true;
        super.onPause();
    }


    @Override
    public void refresh() {
        loadGigs();
    }
}