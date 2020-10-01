package com.example.onlinefreelaceapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.HelperClasses.Utils;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.adapter.GigHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class GigViewActivity extends AppCompatActivity {


    CircleImageView imageView;
    EditText txt_title, txt_category, txt_description, txt_delivery_info, txt_amount, txt_contact;
    private DBHelper dbHelper;
    private int primaryKey;
    Button btnBye;
    private TextView lbl_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig_view);

        dbHelper = new DBHelper(this);

        imageView = findViewById(R.id.img_cover);
        txt_title = findViewById(R.id.txt_title);
        txt_category = findViewById(R.id.txt_category);
        txt_description = findViewById(R.id.txt_description);
        txt_delivery_info = findViewById(R.id.txt_delivery_info);
        txt_amount = findViewById(R.id.txt_amount);
        txt_contact = findViewById(R.id.txt_contact);
        lbl_username = findViewById(R.id.lbl_username);
        findViewById(R.id.btn_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: do your function here...
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            GigHolder gigHolder = dbHelper.getGigsFromPrimaryKey(extras.getInt(Constants.BUNDLE_ID));
            setData(gigHolder);
            primaryKey = extras.getInt(Constants.BUNDLE_ID);
        }

        btnBye = (Button) findViewById(R.id.btn_buy);

        btnBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setData(GigHolder holder) {
        if (holder != null) {
            imageView.setImageURI(holder.getImage());
            txt_title.setText(holder.getTitle());
            txt_category.setText(holder.getCategory());
            txt_description.setText(holder.getDescription());
            txt_delivery_info.setText(holder.getDeliveryInfo());
            txt_amount.setText(Utils.getDecimal(holder.getTotal()));
            txt_contact.setText(holder.getContact());
            lbl_username.setText("Post by : "+holder.getUsername());

        }
    }
}