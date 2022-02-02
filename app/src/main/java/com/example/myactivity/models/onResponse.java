package com.example.myactivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class onResponse {
    private String status;
    private String message;
    @SerializedName("return")
    private List<User> returns = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getReturns() {
        return returns;
    }

    public void setReturns(List<User> returns) {
        this.returns = returns;
    }
}
