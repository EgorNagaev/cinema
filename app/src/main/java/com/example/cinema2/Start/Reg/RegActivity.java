package com.example.cinema2.Start.Reg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinema2.R;
import com.example.cinema2.Start.Main.MainActivity;
import com.example.cinema2.Start.Network.MyRetrofit;
import com.example.cinema2.Start.Network.RetrofitCalls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegActivity extends AppCompatActivity {
    EditText emailreg,passwordreg,lastname,firstname,passwordagg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        emailreg = findViewById(R.id.regemailText);
        passwordreg = findViewById(R.id.regpasswordText);
        passwordagg = findViewById(R.id.regpasswordText2);
        lastname = findViewById(R.id.lastnamelText);
        firstname = findViewById(R.id.firstnamelText);

    }

    public void Registration(View view) {
        if (!emailreg.getText().toString().equals("")&& !passwordreg.getText().toString().equals("") && !passwordagg.getText().toString().equals("") && !lastname.getText().toString().equals("") && !firstname.getText().toString().equals("")){
            if (passwordreg.getText().toString().equals(passwordagg.getText().toString())){
                Retrofit retrofitreg = MyRetrofit.getRetrofit();
                RetrofitCalls regcall = retrofitreg.create(RetrofitCalls.class);
                Call rcall = regcall.registration(emailreg.getText().toString(),passwordreg.getText().toString(),firstname.getText().toString(),lastname.getText().toString());
                rcall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Intent log = new Intent(RegActivity.this,MainActivity.class);
                        startActivity(log);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Пароли не совпадают", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(),"Есть пустые поля", Toast.LENGTH_LONG).show();
        }
    }

    public void Back(View view) {
        Intent back = new Intent(RegActivity.this, MainActivity.class);
        startActivity(back);
    }
}