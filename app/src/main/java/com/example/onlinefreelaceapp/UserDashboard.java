package com.example.onlinefreelaceapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefreelaceapp.Common.AccountCreateSuccessfully;
import com.example.onlinefreelaceapp.Common.BuyerRequestHome;
import com.example.onlinefreelaceapp.Common.LoginSignup.Login;
import com.example.onlinefreelaceapp.Common.LoginSignup.RetailerStartUpScreen;
import com.example.onlinefreelaceapp.Common.LoginSignup.UsersModel;
import com.example.onlinefreelaceapp.Common.PostRequest;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.Common.PostARequestHome;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.MostViewedHelperClass;

import com.example.onlinefreelaceapp.R;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

    public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        RecyclerView featuredRecycler;
        RecyclerView featuredRecyclerTwo;
        RecyclerView featuredRecyclerThree;
        RecyclerView.Adapter adapter;
        ImageView menuIcon,setting;
        UsersModel usersModel;


        //Drawer Menu
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        private DBHelper dbHelper;


        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_user_dashboard);




            dbHelper = new DBHelper(this);

            //Hooks
            featuredRecycler = findViewById(R.id.featured_recycler);
            featuredRecyclerTwo = findViewById(R.id.featured_recycler_two);
            featuredRecyclerThree = findViewById(R.id.featured_recycler_three);
            menuIcon = findViewById(R.id.menu_icon);
            setting = (ImageView) findViewById(R.id.settings);


            //Hooks Navigation
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.navigation_view);


            navigationDrawer();
            featuredRecycler();
            featuredRecyclerTwo();
            featuredRecyclerThree();

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    String username = intent.getStringExtra("username");
                    String pass = intent.getStringExtra("password");
                    Intent intent1 = new Intent(getApplicationContext(), settings.class);
                    intent1.putExtra("username",username);
                    intent1.putExtra("password",pass);
                    startActivity(intent1);

                }
            });


        }




        //Navigation Drawer

        private void navigationDrawer() {

            //Navigation Drawer
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_home);

            menuIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    else drawerLayout.openDrawer(GravityCompat.START);
                }
            });


        }

        @Override
        public void onBackPressed() {

            if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            } else
            super.onBackPressed();
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
            break;
            case R.id.nav_post_request:
                Intent intent = new Intent(getApplicationContext(),PostARequestHome.class);
                intent.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(intent);
                break;
            case R.id.nav_my_orders:
                Intent intent1 = new Intent(getApplicationContext(), OrdersToMeHome.class);
                Intent intent5 = getIntent();
                intent1.putExtra("username",intent5.getStringExtra("username"));
                startActivity(intent1);
                break;
            case R.id.nav_to_be_received:
                Intent intent2 = new Intent(getApplicationContext(), OrdersToBeRecHome.class);
                Intent intent6 = getIntent();
                intent2.putExtra("username",intent6.getStringExtra("username"));
                startActivity(intent2);
                break;
            case R.id.nav_revieworder:
                Intent intent4 = getIntent();
                String intentUsername = intent4.getStringExtra("username");
                Intent intent3 = new Intent(getApplicationContext(), ReviewOrder.class);
                intent3.putExtra("username",intentUsername);
                startActivity(intent3);
                break;
            case R.id.nav_my_gigs:
                Intent intent7 = new Intent(getApplicationContext(), Display_Gigs_page.class);
                intent7.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(intent7);
                break;

            case R.id.nav_buyer_request:
                Intent intent8 = new Intent(getApplicationContext(), BuyerRequestHome.class);
                startActivity(intent8);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //Recycler View

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        featuredRecycler.setAdapter(adapter);

    }

    private void featuredRecyclerTwo() {

        featuredRecyclerTwo.setHasFixedSize(true);
        featuredRecyclerTwo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        featuredRecyclerTwo.setAdapter(adapter);

    }


    private void featuredRecyclerThree() {

        featuredRecyclerThree.setHasFixedSize(true);
        featuredRecyclerThree.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredGigs = new ArrayList<>();

        featuredGigs.add(new FeaturedHelperClass(R.drawable.flyer_design,"Flyer Design","I will do creative business flyer poster design.I\'ll give you Unlimited Modification for your Design."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.digital_marketing_img,"Facebook Marketing","I will create, design and optimize facebook business page."));
        featuredGigs.add(new FeaturedHelperClass(R.drawable.logo_design_img,"Logo Design","I will create professional business logo designs."));

        adapter = new FeaturedAdapter(featuredGigs);
        featuredRecyclerThree.setAdapter(adapter);

    }






}