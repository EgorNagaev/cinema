package com.example.cinema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EpisodeRecycler extends RecyclerView.Adapter<EpisodeRecycler.MyVH> {
    ImageView episodeimage;
    TextView nameepisode,description,yearepisode;
    EpisodeItemItem listepisode;
    Context context;
    String imageURL = "http://cinema.areas.su/up/images/";

    public EpisodeRecycler(EpisodeItemItem listepisode, Context context) {
        this.listepisode = listepisode;
        this.context = context;
    }

    @NonNull
    @Override
    public EpisodeRecycler.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.episod_recycler,parent,false);
        return new MyVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeRecycler.MyVH holder, int position) {
        Glide.with(context).load(imageURL+listepisode.getImages().get(position)).into(episodeimage);

    }

    @Override
    public int getItemCount() {

        return listepisode.getImages().size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        public MyVH(@NonNull View itemView) {
            super(itemView);
            episodeimage = itemView.findViewById(R.id.episodeimage);
            nameepisode = itemView.findViewById(R.id.episodename);
            description = itemView.findViewById(R.id.episodecharacter);
            yearepisode = itemView.findViewById(R.id.episodeyear);
        }
    }
}
