package com.example.onlinefreelaceapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.onlinefreelaceapp.ui.main.SectionsPagerAdapterOrderToBeReceived;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.onlinefreelaceapp.ui.main.SectionsPagerAdapter;

public class OrdersToBeReceived extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_to_be_received);
        SectionsPagerAdapterOrderToBeReceived sectionsPagerAdapterOrderToBeReceived = new SectionsPagerAdapterOrderToBeReceived(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager_orderstobereceived);
        viewPager.setAdapter(sectionsPagerAdapterOrderToBeReceived);
        TabLayout tabs = findViewById(R.id.tabs_orderstobereceived);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab_orderstobereceived);


        back = findViewById(R.id.onbackbtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OrdersToBeReceived.this, UserDashboard.class);
                intent1.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(intent1);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}