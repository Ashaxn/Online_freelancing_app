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

public class PostRequestAdapter extends ArrayAdapter<PostRequestModel> {

    private  Context context;
    private int resource;
    List<PostRequestModel> requestposts;

    PostRequestAdapter(Context context, int resource, List<PostRequestModel> requestposts){
        super(context,resource,requestposts);
        this.context = context;
        this.resource = resource;
        this.requestposts = requestposts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView budget = row.findViewById(R.id.budget);
        ImageView imageView = row.findViewById(R.id.onGoing);

        PostRequestModel postRequestModel = requestposts.get(position);
        title.setText(postRequestModel.getPosttitle());
        budget.setText(postRequestModel.getPostbudget());
        imageView.setVisibility(row.INVISIBLE);

        return row;
    }
}
