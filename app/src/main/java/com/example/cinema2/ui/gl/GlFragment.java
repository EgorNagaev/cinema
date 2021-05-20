package com.example.cinema2.ui.gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinema2.GlActivity;
import com.example.cinema2.MyRecycler;
import com.example.cinema2.R;
import com.example.cinema2.ScrollFilmItem;
import com.example.cinema2.Start.Token.FilmToken;
import com.example.cinema2.Start.Network.MyRetrofit;
import com.example.cinema2.Start.Network.RetrofitCalls;
import com.example.cinema2.WatchActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlFragment extends Fragment {

ImageView posterfilm, nameFilm;
RecyclerView intrendrecycler,newrecycler,formerecycler;
List<ScrollFilmItem> trendlist,newlist,formelist;
String imageURL = "http://cinema.areas.su/up/images/";
Button watch;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gl, container, false);
        posterfilm = root.findViewById(R.id.filmimage);
        intrendrecycler =root.findViewById(R.id.intrend);
        newrecycler = root.findViewById(R.id.newrecycler);
        formerecycler = root.findViewById(R.id.forme);
        nameFilm = root.findViewById(R.id.filmname);
        watch = root.findViewById(R.id.watch);
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent watchintent = new Intent(getActivity(),WatchActivity.class);
                startActivity(watchintent);

            }
        });
        Retrofit filmretrofit = MyRetrofit.getRetrofit();
        RetrofitCalls filmrcall = filmretrofit.create(RetrofitCalls.class);
        Call<FilmToken> filmcall = filmrcall.movies();
        filmcall.enqueue(new Callback<FilmToken>() {
            @Override
            public void onResponse(Call<FilmToken> call, Response<FilmToken> response) {
                Glide.with(getContext()).load(imageURL+response.body().getBackgroundImage()).into(posterfilm);
                Glide.with(getContext()).load(imageURL+response.body().getForegroundImage()).into(nameFilm);
            }

            @Override
            public void onFailure(Call<FilmToken> call, Throwable t) {

            }
        });
        Call<List<ScrollFilmItem>> trendcall = filmrcall.getMovies("inTrend");
        Call<List<ScrollFilmItem>> newcall = filmrcall.getMovies("new");
        Call<List<ScrollFilmItem>> mecall = filmrcall.getMovies("forMe");
        trendcall.enqueue(new Callback<List<ScrollFilmItem>>() {
            @Override
            public void onResponse(Call<List<ScrollFilmItem>> call, Response<List<ScrollFilmItem>> response) {
                trendlist = response.body();
                MyRecycler trendrecycler = new MyRecycler(getContext(),trendlist);
                intrendrecycler.setAdapter(trendrecycler);
            }

            @Override
            public void onFailure(Call<List<ScrollFilmItem>> call, Throwable t) {

            }
        });
        newcall.enqueue(new Callback<List<ScrollFilmItem>>() {
            @Override
            public void onResponse(Call<List<ScrollFilmItem>> call, Response<List<ScrollFilmItem>> response) {
                newlist = response.body();
                MyRecycler news = new MyRecycler(getContext(), newlist);
                newrecycler.setAdapter(news);
            }

            @Override
            public void onFailure(Call<List<ScrollFilmItem>> call, Throwable t) {

            }
        });
        mecall.enqueue(new Callback<List<ScrollFilmItem>>() {
            @Override
            public void onResponse(Call<List<ScrollFilmItem>> call, Response<List<ScrollFilmItem>> response) {
             formelist = response.body();
             MyRecycler me = new MyRecycler(getContext(), formelist);
             formerecycler.setAdapter(me);
            }

            @Override
            public void onFailure(Call<List<ScrollFilmItem>> call, Throwable t) {

            }
        });






        return root;
    }
}