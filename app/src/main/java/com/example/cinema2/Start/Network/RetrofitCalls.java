package com.example.cinema2.Start.Network;

import com.example.cinema2.EpisodeItem;
import com.example.cinema2.EpisodeItemItem;
import com.example.cinema2.ScrollFilmItem;
import com.example.cinema2.Start.Token.FilmToken;
import com.example.cinema2.Start.Token.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitCalls {
    @POST("/auth/login")
    Call<Token>login(@Query("email") String email,
                @Query("password")String password);
    @POST("/auth/register")
    Call<Token>registration(@Query("email") String email,
                            @Query("password")String password,
                            @Query("firstname")String firstname,
                            @Query("lastname")String lastname);
    @GET ("/movies/cover")
    Call<FilmToken>movies();
    @GET("/movies")
    Call<List<ScrollFilmItem>>getMovies(@Query("filter") String type);
    @GET("/movies/{movieId}")
    Call<ScrollFilmItem> getOneMovie(@Path("movieId") String movieId);
    @GET("/movies/{movieId}/episodes")
    Call<List<EpisodeItemItem>>getEpisode();
}
