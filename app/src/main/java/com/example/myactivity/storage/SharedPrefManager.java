//package com.example.myactivity.storage;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import com.example.myactivity.models.User;
//
//
//public class SharedPrefManager {
//
//    private static final String SHARED_PREF_NAME = "my_shared_pref";
//
//    private static SharedPrefManager mInstance;
//    private Context mCtx;
//
//    private SharedPrefManager(Context mCtx) {
//        this.mCtx = mCtx;
//    }
//
//
//    public static synchronized SharedPrefManager getInstance(Context mCtx) {
//        if (mInstance == null) {
//            mInstance = new SharedPrefManager(mCtx);
//        }
//        return mInstance;
//    }
//
//
//    public void saveUser(User user) {
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("username", user.getUsername());
//        editor.putString("password", user.getPassword());
//        editor.putString("firstname", user.getFirstname());
//        editor.putString("lastname", user.getLastname());
//        editor.putString("address", user.getAddress());
//        editor.putString("contact_number", user.getContact_number());
//        editor.putString("db_identifier", "UNILEVER");
//        editor.apply();
//
//    }
//
//
//    public User getUser() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new User(
//
//                sharedPreferences.getString("username", null),
//                sharedPreferences.getString("password", null),
//                sharedPreferences.getString("firstname", null),
//                sharedPreferences.getString("lastname", null),
//                sharedPreferences.getString("address", null),
//                sharedPreferences.getString("contact_number", null),
//                sharedPreferences.getString("db_identifier", "UNILEVER")
//        );
//    }
//
//    public void clear() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//    }
//
//}