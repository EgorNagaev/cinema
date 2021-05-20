package com.example.cinema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinema2.Start.Network.MyRetrofit;
import com.example.cinema2.Start.Network.RetrofitCalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WatchActivity extends AppCompatActivity {
    ImageView logofilm,cadrs;
    TextView namesfilm, characteristic;
    TextView ageFilm;
    String movieId;
    String imageURL = "http://cinema.areas.su/up/images/";
    RecyclerView  episodeRecycler,cadrRecycler;
    List<ScrollFilmItem> cadrlist;
    List<EpisodeItemItem> episodelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        movieId = getIntent().getStringExtra("movieID");
        logofilm  = findViewById(R.id.filmlogotype);
        cadrRecycler = findViewById(R.id.cadrrecycler);
        episodeRecycler = findViewById(R.id.episoderecycler);
        namesfilm = findViewById(R.id.namefilm);
        characteristic = findViewById(R.id.characterfisticfilm);
        Retrofit newretrofit = MyRetrofit.getRetrofit();
        ageFilm = findViewById(R.id.age);
        RetrofitCalls filmcalls = newretrofit.create(RetrofitCalls.class);
        Call<ScrollFilmItem> call = filmcalls.getOneMovie(movieId);
        call.enqueue(new Callback<ScrollFilmItem>() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ScrollFilmItem> call, Response<ScrollFilmItem> response) {
                Glide.with(getApplicationContext()).load(imageURL+response.body().getPoster()).into(logofilm);
                namesfilm.setText(response.body().getName());
                characteristic.setText(response.body().getDescription());
                switch (response.body().getAge())
                {
                    case "0":
                        ageFilm.setText("0+");
                        ageFilm.setTextColor(R.color.white);
                        break;
                    case "18":
                        ageFilm.setText("18+");
                        ageFilm.setTextColor(R.color.orange);
                        break;
                    case "16":
                        ageFilm.setText("16+");
                        ageFilm.setTextColor(R.color.sixteen);
                        break;
                    case "12":
                        ageFilm.setText("12+");
                        ageFilm.setTextColor(R.color.tvelve);
                        break;
                    case "6":
                        ageFilm.setText("6+");
                        ageFilm.setTextColor(R.color.six);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ScrollFilmItem> call, Throwable t) {

            }
        });
        Call<ScrollFilmItem> cadrscall = filmcalls.getOneMovie(movieId);
        cadrscall.enqueue(new Callback<ScrollFilmItem>() {
            @Override
            public void onResponse(Call<ScrollFilmItem> call, Response<ScrollFilmItem> response) {
                ScrollFilmItem film = response.body();
                CadrRecycler cadrecycler = new CadrRecycler(getApplicationContext(),film);
                cadrRecycler.setAdapter(cadrecycler);
            }

            @Override
            public void onFailure(Call<ScrollFilmItem> call, Throwable t) {

            }
        });



    }
}