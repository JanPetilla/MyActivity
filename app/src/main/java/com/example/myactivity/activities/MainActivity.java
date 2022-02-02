package com.example.myactivity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myactivity.API.RetrofitClient;
import com.example.myactivity.R;
import com.example.myactivity.models.User;
import com.example.myactivity.models.onResponse;
import com.google.gson.Gson;
//import com.example.myactivity.storage.SharedPrefManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    private EditText edUsername, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);


        findViewById(R.id.btnSignUp).setOnClickListener(this);
        findViewById(R.id.tvLogin).setOnClickListener(this);
    }


    private void userSignup() {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();


        if (username.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else{

        }


        User user = new User();
        user.setUsername(edUsername.getText().toString());
        user.setPassword(edPassword.getText().toString());
        user.setDb_identifier("UNILEVER");
        user.setAddress("none");
        user.setFirstname("none");
        user.setLastname("none");
        user.setContact_number("none");
        Log.d("Mainactivity", new Gson().toJson(user));
        Call<onResponse> login = RetrofitClient
                .getRetrofitClient()
                .getApi()
                .signup(user);


        login.enqueue(new Callback<onResponse>() {
            @Override
            public void onResponse(Call<onResponse> call, Response<onResponse> response) {

                String s = null;
                Log.d("signup1", new Gson().toJson(response.body()));

                    if (response.code() == 200) {
                        s = response.body().getStatus();

                        if(s.contains("success")) {

                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                            Boolean checkUsername = databaseHelper.checkUsername(username);
                            if(checkUsername==false){
                                Boolean insert = databaseHelper.insertData(username, password);
                                if(insert==true){
                                    Log.d("signup", new Gson().toJson(user));
                                    proceedLogin();
                                }
                            }
                        }
                    } else if (response.code() == 200){
                        s = response.body().getStatus();
                        if(s.contains("error")) {
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                        }
                    }
            }

            @Override
            public void onFailure(Call<onResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proceedLogin() {
        startActivity(new Intent(this, LoginActivity.class));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSignUp:
                userSignup();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

    }
}