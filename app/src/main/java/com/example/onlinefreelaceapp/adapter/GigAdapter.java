package com.example.onlinefreelaceapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onlinefreelaceapp.Create_A_Gig;
import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.HelperClasses.UiRefresh;
import com.example.onlinefreelaceapp.HelperClasses.Utils;
import com.example.onlinefreelaceapp.R;
import com.example.onlinefreelaceapp.ui.main.GigViewActivity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class GigAdapter extends RecyclerView.Adapter<GigAdapter.ViewHolder> {
    private static final String TAG = "MultipleStopAdapter";
    private List<GigHolder> gigList;
    private Activity activity;
    private boolean isAdminView;
    private DBHelper dbHelper;
    private UiRefresh uiRefresh;
    private String seller;

    public GigAdapter(Activity activity, boolean isAdminView, DBHelper dbHelper,UiRefresh uiRefresh, String seller) {
        this.activity = activity;
        gigList = new ArrayList<>();
        this.isAdminView = isAdminView;
        this.dbHelper = dbHelper;
        this.uiRefresh = uiRefresh;
        this.seller = seller;
    }

    public GigAdapter(Activity activity, boolean isAdminView, DBHelper dbHelper,UiRefresh uiRefresh) {
        this.activity = activity;
        gigList = new ArrayList<>();
        this.isAdminView = isAdminView;
        this.dbHelper = dbHelper;
        this.uiRefresh = uiRefresh;
    }


    public void addAll(List<GigHolder> locationList) {
        this.gigList.addAll(locationList);
        notifyDataSetChanged();
    }

    public void clear() {
        gigList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_gig_item, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final GigHolder gigHolder = gigList.get(position);


        holder.lbl_title.setText(gigHolder.getTitle());
        holder.lbl_price.setText(Utils.getDecimal(gigHolder.getTotal()));
        holder.lbl_contact.setText(gigHolder.getContact());
        holder.img_cover.setImageURI(gigHolder.getImage());
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(!isAdminView){
                   Intent intent = new Intent(activity, GigViewActivity.class);
                   intent.putExtra(Constants.BUNDLE_ID, gigHolder.getPrimaryKey());
                   intent.putExtra("username",seller);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   activity.startActivity(intent);
               }

                if(!isAdminView){
                    Intent intent = new Intent(activity, GigViewActivity.class);
                    intent.putExtra(Constants.BUNDLE_ID, gigHolder.getPrimaryKey());
                    intent.putExtra("username",seller);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                }

            }
        });

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Create_A_Gig.class);
                intent.putExtra(Constants.BUNDLE_ID, gigHolder.getPrimaryKey());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(activity).setTitle("Confirm").setMessage("Are you sure delete this ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteGig(gigHolder.getPrimaryKey());
                        gigList.remove(position);
                        notifyItemRemoved(position);
                        uiRefresh.refresh();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
        holder.img_edit.setVisibility(isAdminView ? View.VISIBLE : View.GONE);
        holder.img_delete.setVisibility(isAdminView ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return gigList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll_main;
        private ImageView img_edit, img_cover, img_delete;
        private TextView lbl_title, lbl_price, lbl_contact;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_main = itemView.findViewById(R.id.ll_main);
            img_cover = itemView.findViewById(R.id.img_cover);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_price = itemView.findViewById(R.id.lbl_amount);
            lbl_contact = itemView.findViewById(R.id.lbl_contact);


        }
    }
}
