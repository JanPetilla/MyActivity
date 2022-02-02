package com.example.myactivity.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactivity.API.RetrofitClient;
import com.example.myactivity.R;
import com.example.myactivity.models.User;
import com.example.myactivity.models.onResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    private List<User> list = new ArrayList<>();
    private Context context;
    DatabaseHelper databaseHelper;

    public DataAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_users, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        User user = list.get(position);
        holder.username1.setText(user.getUsername());
        holder.password1.setText(user.getPassword());
        holder.firstname.setText(user.getFirstname());
        holder.lastname.setText(user.getLastname());
        holder.contact_number.setText(user.getContact_number());
        holder.address.setText(user.getAddress());


        holder.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("user", new Gson().toJson(user));
                context.startActivity(intent);
            }
        });

        holder.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                if (((ProfileActivity) context) != null && ((ProfileActivity) context).isFinishing()) {
                    return;
                }


                builder.setTitle("Are you sure you want to delete this user?");
                builder.setMessage("This action cannot be undone");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        databaseHelper = new DatabaseHelper(context);
                        Boolean checkDelete = databaseHelper.deleteUserData(user);
                        Log.d("checkDelete", checkDelete.toString());
                        if (checkDelete == true) {

                            Log.d("DeleteUser", new Gson().toJson(user));
                            Call<onResponse> delete = RetrofitClient
                                    .getRetrofitClient()
                                    .getApi()
                                    .deleteUser(user.getUsername(), "UNILEVER");

                            delete.enqueue(new Callback<onResponse>() {
                                @Override
                                public void onResponse(Call<onResponse> call, Response<onResponse> response) {

                                    if (response.body().getStatus().equals("success")) {
                                        Log.d("delete", new Gson().toJson(response.body()));
                                        listOfUsers();
                                    }
                                }
                                @Override
                                public void onFailure(Call<onResponse> call, Throwable t) {

                                }
                            });

                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    private void listOfUsers() {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
