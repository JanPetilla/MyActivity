package com.example.myactivity.activities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactivity.R;


public class DataViewHolder extends RecyclerView.ViewHolder {

    TextView username1, password1, firstname, lastname, contact_number, address;
    Button btnEditUser, btnDeleteUser;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);

        username1 = itemView.findViewById(R.id.tvUsername1);
        password1 = itemView.findViewById(R.id.tvPassword1);
        firstname = itemView.findViewById(R.id.tvFirstname);
        lastname = itemView.findViewById(R.id.tvLastname);
        contact_number = itemView.findViewById(R.id.tvContact);
        address = itemView.findViewById(R.id.tvAddress);
        btnEditUser = itemView.findViewById(R.id.btnEditUser);
        btnDeleteUser = itemView.findViewById(R.id.btnDeleteUser);
    }
}
