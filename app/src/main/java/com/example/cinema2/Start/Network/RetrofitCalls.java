package com.example.cinema2.Start.Network;

import com.example.cinema2.Start.FilmToken;
import com.example.cinema2.Start.Token.Token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}
