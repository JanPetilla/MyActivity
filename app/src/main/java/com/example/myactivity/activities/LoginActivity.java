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
import com.example.myactivity.models.LoginResponse;
import com.example.myactivity.models.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edUsername;
    private EditText edPassword;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.tvRegister).setOnClickListener(this);
        edPassword = findViewById(R.id.edPassword);
        edUsername = findViewById(R.id.edUsername);
    }

    private void userLogin() {

        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        Boolean checkLogin = databaseHelper.checkLogin(username, password);

        if(checkLogin==true){
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            proceedListOfUsers();

        }else{
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }



        User user = new User();
        user.setUsername(edUsername.getText().toString());
        user.setPassword(edPassword.getText().toString());
        user.setDb_identifier("UNILEVER");

        Log.d("login", new Gson().toJson(user));

        Call<LoginResponse> call = RetrofitClient
                .getRetrofitClient()
                .getApi()
                .loginUser(user.getUsername(), user.getPassword(), user.getDb_identifier());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                String s = null;

                try {
                    if (response.code() == 200) {
                        s = response.body().getStatus();
                        if(s.contains("success")) {
                            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
                            proceedListOfUsers();
                        }
                    } else {
                        s = response.errorBody().string();
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }


    private void proceedListOfUsers() {
        startActivity(new Intent(this, ProfileActivity.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.tvRegister:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}