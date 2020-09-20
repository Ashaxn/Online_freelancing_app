package com.example.onlinefreelaceapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefreelaceapp.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredGigs;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredGigs) {
        this.featuredGigs = featuredGigs;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass = featuredGigs.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {

        return featuredGigs.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);

        }
    }

}
