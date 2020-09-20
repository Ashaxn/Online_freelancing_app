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

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {

    ArrayList<MostViewedHelperClass> mostViewedGigs;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedGigs) {
        this.mostViewedGigs = mostViewedGigs;
    }

    @NonNull
    @Override
    public MostViewedAdapter.MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        MostViewedAdapter.MostViewedViewHolder mostViewedViewHolder = new MostViewedAdapter.MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedAdapter.MostViewedViewHolder holder, int position) {

        MostViewedHelperClass mostViewedHelperClass = mostViewedGigs.get(position);

        holder.image.setImageResource(mostViewedHelperClass.getImage());
        holder.title.setText(mostViewedHelperClass.getTitle());
        holder.desc.setText(mostViewedHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {

        return mostViewedGigs.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);

        }
    }

}

