package com.example.onlinefreelaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.adapter.GigHolder;

import java.net.MalformedURLException;
import java.net.URL;

public class ReviewOrder extends AppCompatActivity {

    EditText email,requirement,driveLink;
    TextView total_price,subtotal,service_charge,title,description,byseller;
    Button addOrder;
    DBHelper DB;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order);

        email = (EditText) findViewById(R.id.txtWorkingEmailOrder);
        requirement = (EditText) findViewById(R.id.txtOrderRequirement);
        driveLink = (EditText) findViewById(R.id.linkOrderResource);
        total_price = (TextView) findViewById(R.id.txtOrderTotal);
        subtotal = (TextView) findViewById(R.id.txtOrderSubTotal);
        service_charge = (TextView) findViewById(R.id.txtOrderServiceCharge);
        title = (TextView) findViewById(R.id.gigtitleorderreview);
        description = (TextView) findViewById(R.id.gigdescriptionorder);
        byseller = (TextView) findViewById(R.id.orderBySeller);
        imageView = (ImageView) findViewById(R.id.gigimageorder);

        DB = new DBHelper(this);

        final GigHolder gigHolder = DB.getGigsFromPrimaryKey(Integer.parseInt(getIntent().getStringExtra("gigid")));

        imageView.setImageURI(gigHolder.getImage());

        byseller.setText("Gig Posted By "+getIntent().getStringExtra("seller"));

        subtotal.setText(getIntent().getStringExtra("amountOne"));
        subtotal.setText(getIntent().getStringExtra("amountTwo"));

        //imageView.setImageURI(getIntent().getStringExtra("image"));

        title.setText(getIntent().getStringExtra("gigTitle"));
        description.setText(getIntent().getStringExtra("desciption"));


        addOrder = (Button) findViewById(R.id.btnAddOrder);

        Intent intentUsername = getIntent();
        String user = intentUsername.getStringExtra("username");



        String txtEmail = DB.getUserEmailAddress(getIntent().getStringExtra("username"));
        email.setText(txtEmail);

//        String txtEmail = DB.getUserEmail(user);

  //      email.setText(txtEmail);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                String buyer = intent1.getStringExtra("username");
                String seller = getIntent().getStringExtra("seller");
                int gigId = 12;
                String workingEmail = email.getText().toString();
                String orderReq = requirement.getText().toString();
                String resourceLink = driveLink.getText().toString();

                long publishDate = System.currentTimeMillis();


                int totPrice = Integer.parseInt(total_price.getText().toString());
                int subTotal = Integer.parseInt(subtotal.getText().toString());
                int serviceCharge = Integer.parseInt(service_charge.getText().toString());

                Orders order = new Orders(subTotal,serviceCharge,totPrice,gigId,workingEmail,resourceLink,orderReq,buyer,seller,publishDate,0);



                if(order.getWork_email().equals("")) {
                    Toast.makeText(ReviewOrder.this,"Please Enter Working Email",Toast.LENGTH_SHORT).show();
                }else if(order.getReq().equals("")) {
                    Toast.makeText(ReviewOrder.this,"Please Enter Order Requirement",Toast.LENGTH_SHORT).show();
                }else {

                    if(URLUtil.isValidUrl(order.getResource()) && Patterns.WEB_URL.matcher(order.getResource()).matches() || order.getResource().equals("")) {
                        Boolean insert = DB.insertOrder(order);
                        if (insert == true) {
                            Toast.makeText(ReviewOrder.this, "Order Successful!", Toast.LENGTH_SHORT).show();
                            Intent intentPaymentPage = new Intent(getApplicationContext(), OrderSuccess.class);
                            intentPaymentPage.putExtra("username",getIntent().getStringExtra("username"));
                            intentPaymentPage.putExtra("buyerUsername", order.getBuyer());
                            intentPaymentPage.putExtra("sellerUsername", order.getSeller());
                            intentPaymentPage.putExtra("paymentTotal", order.getTotal());
                            intentPaymentPage.putExtra("PaymentSub", order.getSubTot());
                            startActivity(intentPaymentPage);
                        } else {
                            Toast.makeText(ReviewOrder.this, "Order Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ReviewOrder.this, "Please Enter Valid URL!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }



}