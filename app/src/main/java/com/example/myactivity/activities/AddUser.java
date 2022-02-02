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
import com.example.myactivity.models.AddResponse;
import com.example.myactivity.models.User;
import com.example.myactivity.models.onResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity implements View.OnClickListener{

    private EditText edUsername1, edPassword1, edFirstname, edLastname, edContact, edAddress;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        String userStr = getIntent().getStringExtra("user");
        User user = new Gson().fromJson(userStr, User.class);
        databaseHelper = new DatabaseHelper(this);

        edUsername1 = findViewById(R.id.edUsername1);
        edPassword1 = findViewById(R.id.edPassword1);
        edFirstname = findViewById(R.id.edFirstname);
        edLastname = findViewById(R.id.edLastname);
        edContact = findViewById(R.id.edContact);
        edAddress = findViewById(R.id.edAddress);

        findViewById(R.id.btnSubmit).setOnClickListener(this);

    }

    private void addUser(){

        String username = edUsername1.getText().toString().trim();
        String password = edPassword1.getText().toString().trim();
        String firstname = edFirstname.getText().toString().trim();
        String lastname = edLastname.getText().toString().trim();
        String contact_number = edContact.getText().toString().trim();
        String address = edAddress.getText().toString().trim();

        Boolean addUser = databaseHelper.addUserData(username, password,firstname,lastname,contact_number,address);

        if (addUser==true){
            Toast.makeText(AddUser.this, "Successfully added user to database", Toast.LENGTH_SHORT).show();
            proceedListOfUsers();
        }
        else{
            Toast.makeText(AddUser.this, "Failed to add user to database", Toast.LENGTH_SHORT).show();
        }

        User user = new User();
        user.setUsername(edUsername1.getText().toString());
        user.setPassword(edPassword1.getText().toString());
        user.setFirstname(edFirstname.getText().toString());
        user.setLastname(edLastname.getText().toString());
        user.setContact_number(edContact.getText().toString());
        user.setAddress(edAddress.getText().toString());
        user.setDb_identifier("UNILEVER");


        Log.d("add", new Gson().toJson(user));

        Call<onResponse> call = RetrofitClient
                .getRetrofitClient()
                .getApi()
                .addUser(user);

        call.enqueue(new Callback<onResponse>() {
            @Override
            public void onResponse(Call<onResponse> call, Response<onResponse> response) {
                String s = null;

                    Log.d("addUser", new Gson().toJson(response.body()));
                    Toast.makeText(AddUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                    if(response.body().getStatus().equals("success")){
                        proceedListOfUsers();

                    } else {

                    }
                }

            @Override
            public void onFailure(Call<onResponse> call, Throwable t) {

            }
        });
    }

    private void proceedListOfUsers() {
        startActivity(new Intent(this, ProfileActivity.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                addUser();
                break;
        }

    }
}