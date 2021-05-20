package com.example.cinema2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class MyRecycler extends RecyclerView.Adapter<MyRecycler.MyVH> {
    ImageView imageView;
    Context context;
    List<ScrollFilmItem> list;

    public MyRecycler(Context context, List<ScrollFilmItem> list) {
        this.context = context;
        this.list = list;
    }

    String imageURL = "http://cinema.areas.su/up/images/";

    @NonNull
    @Override
    public MyRecycler.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.film_recycler,parent,false);
        return new MyVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycler.MyVH holder, int position) {
        Glide.with(context).load(imageURL+list.get(position).getPoster()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WatchActivity.class);
                intent.putExtra("movieID",list.get(position).getMovieId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.filmrecycler);
        }
    }
}
