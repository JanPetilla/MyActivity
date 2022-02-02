package com.example.myactivity.API;

import com.example.myactivity.models.AddResponse;
import com.example.myactivity.models.ListResponse;
import com.example.myactivity.models.LoginResponse;
import com.example.myactivity.models.User;
import com.example.myactivity.models.onResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
//
//    @POST("packusers")
//    Call<ResponseBody> createUser(
//            @Field("username") String username,
//            @Field("password") String password
//    );

    @POST("packuserslogin")
    Call<LoginResponse> loginUser(
            @Query("username") String username,
            @Query("password") String password,
            @Query("db_identifier") String db_identifier
    );

    @POST("packusers")
    Call<onResponse> addUser(@Body User user);
//            @Query("firstname") String firstname,
//            @Query("lastname") String lastname,
//            @Query("contact_number") String contact_number,
//            @Query("address") String address,
//            @Query("db_identifier") String db_identifier


    @POST("packusers")
    Call<onResponse> signup(@Body User user);

//    @POST("packuserslogin")
//    Call<LoginResponse> login(@QueryMap User user);

    @GET("packusers")
    Call<onResponse> getUsers(@QueryMap HashMap<String, String> hashMap);

    @PUT("packusers")
    Call<onResponse> editUsers(@Body User user);

    @DELETE("packusers")
    Call<onResponse> deleteUser(
            @Query("username") String username,
            @Query("db_identifier") String db_identifier
    );

}
