package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.onlinefreelaceapp.Common.PostRequest;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.onlinefreelaceapp.OrdersToBeReceived;
import com.example.onlinefreelaceapp.OrdersToMe;
import com.example.onlinefreelaceapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {

    RecyclerView favouriteRecycler;
    RecyclerView favouriteRecyclerTwo;
    RecyclerView favouriteRecyclerThree;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favorites);


        //Hooks
        favouriteRecycler = findViewById(R.id.favourite_recycler);
        favouriteRecyclerTwo = findViewById(R.id.favourite_recycler_two);
        favouriteRecyclerThree = findViewById(R.id.favourite_recycler_three);
        menuIcon = findViewById(R.id.menu_icon);

        favouriteRecycler();
        favouriteRecyclerTwo();
        favouriteRecyclerThree();

    }



    //Recycler View

    private void favouriteRecycler() {

        favouriteRecycler.setHasFixedSize(true);
        favouriteRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        favouriteRecycler.setAdapter(adapter);

    }

    private void favouriteRecyclerTwo() {

        favouriteRecyclerTwo.setHasFixedSize(true);
        favouriteRecyclerTwo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        favouriteRecyclerTwo.setAdapter(adapter);

    }


    private void favouriteRecyclerThree() {

        favouriteRecyclerThree.setHasFixedSize(true);
        favouriteRecyclerThree.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        favouriteRecyclerThree.setAdapter(adapter);

    }


}