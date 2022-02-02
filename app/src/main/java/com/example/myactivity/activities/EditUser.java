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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUser extends AppCompatActivity implements View.OnClickListener {

    private EditText edFirstname, edLastname, edContact, edAddress;
    private User user;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edFirstname = findViewById(R.id.edFirstname);
        edLastname = findViewById(R.id.edLastname);
        edContact = findViewById(R.id.edContact);
        edAddress = findViewById(R.id.edAddress);

        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();

        String userString = bundle.getString("user", "");
        user = new Gson().fromJson(userString, User.class);

        Log.d("EditUser", ">>>>>> user converted >??? "+new Gson().toJson(user));
        edFirstname.setText(user.getFirstname());
        edLastname.setText(user.getLastname());
        edContact.setText(user.getContact_number());
        edAddress.setText(user.getAddress());
        findViewById(R.id.btnUpdate).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnUpdate:

                user.setFirstname(edFirstname.getText().toString());
                user.setLastname(edLastname.getText().toString());
                user.setContact_number(edContact.getText().toString());
                user.setAddress(edAddress.getText().toString());
                user.setDb_identifier("UNILEVER");

                Boolean checkUpdate = databaseHelper.updateUserData(user);
                if (checkUpdate==true){
                    Log.d("EditUser", new Gson().toJson(user));
                    Call<onResponse> edit = RetrofitClient
                            .getRetrofitClient()
                            .getApi()
                            .editUsers(user);

                    edit.enqueue(new Callback<onResponse>() {
                        @Override
                        public void onResponse(Call<onResponse> call, Response<onResponse> response) {
                            if(response.body().getStatus().equals("success")){
                                Log.d("update", new Gson().toJson(response.body()));
                                finish();
                            }
                            Toast.makeText(EditUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<onResponse> call, Throwable t) {

                        }
                    });
                    Toast.makeText(EditUser.this, "Successfully updated user in database", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditUser.this, "Failed to update user in database", Toast.LENGTH_SHORT).show();
                }
        }
    }
}