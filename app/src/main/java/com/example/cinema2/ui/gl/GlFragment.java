package com.example.cinema2.ui.gl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cinema2.R;
import com.example.cinema2.Start.FilmToken;
import com.example.cinema2.Start.Network.MyRetrofit;
import com.example.cinema2.Start.Network.RetrofitCalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlFragment extends Fragment {

ImageView posterfilm, nameFilm;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gl, container, false);
        posterfilm = root.findViewById(R.id.filmimage);
        nameFilm = root.findViewById(R.id.filmname);
        Retrofit filmretrofit = MyRetrofit.getRetrofit();
        RetrofitCalls filmrcall = filmretrofit.create(RetrofitCalls.class);
        Call<FilmToken> filmcall = filmrcall.movies();
        filmcall.enqueue(new Callback<FilmToken>() {
            @Override
            public void onResponse(Call<FilmToken> call, Response<FilmToken> response) {
                Glide.with(getContext()).load("http://cinema.areas.su/up/images/"+response.body().getBackgroundImage()).into(posterfilm);
                Glide.with(getContext()).load("http://cinema.areas.su/up/images/"+response.body().getForegroundImage()).into(nameFilm);
            }

            @Override
            public void onFailure(Call<FilmToken> call, Throwable t) {

            }
        });





        return root;
    }
}