package com.example.cinema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CadrRecycler extends RecyclerView.Adapter<CadrRecycler.MyVH> {
    ImageView cadrView;
    Context context;
    ScrollFilmItem cadrlist;
    String imageURL = "http://cinema.areas.su/up/images/";

    public CadrRecycler(Context context, ScrollFilmItem cadrlist) {
        this.context = context;
        this.cadrlist = cadrlist;
    }

    @NonNull
    @Override
    public CadrRecycler.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.cadr_recycler,parent,false);

        return new MyVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CadrRecycler.MyVH holder, int position) {
        Glide.with(context).load(imageURL+cadrlist.getImages().get(position)).into(cadrView);
    }

    @Override
    public int getItemCount() {
        return cadrlist.getImages().size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        public MyVH(@NonNull View itemView) {
            super(itemView);
            cadrView = itemView.findViewById(R.id.cadrsrec);
        }
    }
}
