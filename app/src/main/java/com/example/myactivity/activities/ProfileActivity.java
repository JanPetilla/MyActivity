package com.example.myactivity.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myactivity.API.Api;
import com.example.myactivity.API.RetrofitClient;
import com.example.myactivity.R;
import com.example.myactivity.models.AddResponse;
import com.example.myactivity.models.ListResponse;
import com.example.myactivity.models.LoginResponse;
import com.example.myactivity.models.User;
import com.example.myactivity.models.onResponse;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import com.example.myactivity.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.btnAddUser).setOnClickListener(this);
        getUsers();

    }

    public void getUsers() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("db_identifier", "UNILEVER");

        Call<onResponse> listCall = RetrofitClient
                .getRetrofitClient()
                .getApi()
                .getUsers(hashMap);


        listCall.enqueue(new Callback<onResponse>() {
            @Override
            public void onResponse(Call<onResponse> call, Response<onResponse> response) {

                if (response.body().getReturns().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "No users found", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("response", ">>>>>>" + new Gson().toJson(response.body()));
                    showList(response.body().getReturns());
                    for(int i=0; i < response.body().getReturns().size(); i++){
                        databaseHelper.insertUsers(response.body().getReturns().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<onResponse> call, Throwable t) {

            }
        });

    }

    private void showList(List<User> response) {

        DataAdapter dataAdapter = new DataAdapter(response, this);

        recyclerView.setAdapter(dataAdapter);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddUser:
                startActivity(new Intent(this, AddUser.class));
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsers();

    }
}