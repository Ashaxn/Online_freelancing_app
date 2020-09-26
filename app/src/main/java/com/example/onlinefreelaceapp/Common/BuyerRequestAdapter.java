package com.example.onlinefreelaceapp.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onlinefreelaceapp.R;

import java.util.List;

public class BuyerRequestAdapter extends ArrayAdapter<PostRequestModel> {

    private Context context;
    private int resource;
    List<PostRequestModel> buyerrequestposts;

    BuyerRequestAdapter(Context context, int resource, List<PostRequestModel> buyerrequestposts){
        super(context,resource,buyerrequestposts);
        this.context = context;
        this.resource = resource;
        this.buyerrequestposts = buyerrequestposts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View reqrow = inflater.inflate(resource,parent,false);

        TextView requestPosttitle = reqrow.findViewById(R.id.requesttitle);
        TextView requestPostbudget = reqrow.findViewById(R.id.requestbudget);
        ImageView requestPostimageView = reqrow.findViewById(R.id.requestonGoing);

        PostRequestModel buyerRequestModel = buyerrequestposts.get(position);
        requestPosttitle.setText(buyerRequestModel.getPosttitle());
        requestPostbudget.setText(buyerRequestModel.getPostbudget());
        requestPostimageView.setVisibility(reqrow.INVISIBLE);

        return reqrow;
    }
}

