package com.example.cinema2.Start.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinema2.GlActivity;
import com.example.cinema2.R;
import com.example.cinema2.Start.Network.MyRetrofit;
import com.example.cinema2.Start.Network.RetrofitCalls;
import com.example.cinema2.Start.Reg.RegActivity;
import com.example.cinema2.Start.Token.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
    }


    public void Login(View view) {
        if(!email.getText().toString().equals("") && !password.getText().toString().equals(""))
        {
            Retrofit retrofitlog = MyRetrofit.getRetrofit();
            RetrofitCalls calllog = retrofitlog.create(RetrofitCalls.class);
            Call<Token> newcalllog = calllog.login(email.getText().toString(),password.getText().toString());
            newcalllog.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (response.body() != null){
                    editor.putInt("token", Integer.parseInt(response.body().getToken()));
                    editor.apply();
                    Intent login = new Intent(MainActivity.this, GlActivity.class);
                    startActivity(login);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Данные пользователя введены неверно",Toast.LENGTH_LONG).show();
                    }}

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                }
            });
        }
        else
        {
            Toast.makeText(this, "Поля пустые", Toast.LENGTH_LONG).show();
        }
    }

    public void Reg(View view) {
        Intent reg = new Intent(MainActivity.this,RegActivity.class);
        startActivity(reg);
    }
}