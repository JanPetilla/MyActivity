package com.example.myactivity.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myactivity.models.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "login.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(username TEXT PRIMARY KEY, password TEXT, firstname TEXT, lastname TEXT, contact_number TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("CREATE TABLE user(username TEXT PRIMARY KEY, password TEXT, firstname TEXT, lastname TEXT, contact_number TEXT, address TEXT)");
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUsers(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("firstname", user.getFirstname());
        contentValues.put("lastname", user.getLastname());
        contentValues.put("contact_number", user.getContact_number());
        contentValues.put("address", user.getAddress());


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{user.getUsername()});
        cursor.moveToFirst();

        if (cursor.getCount() <= 0) {

            sqLiteDatabase.insert("user", null, contentValues);

            return true;
        } else if (cursor.getCount() >= 1) {
            sqLiteDatabase.update("user", contentValues, "username=?", new String[]{user.getUsername()});
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addUserData(String username, String password, String firstname, String lastname, String contact_number, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("contact_number", contact_number);
        contentValues.put("address", address);
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateUserData(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("firstname", user.getFirstname());
        contentValues.put("lastname", user.getLastname());
        contentValues.put("contact_number", user.getContact_number());
        contentValues.put("address", user.getAddress());
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username= ? ", new String[]{user.getUsername()});

        if (cursor.getCount() > 0) {

            long result = sqLiteDatabase.update("user", contentValues, "username=?", new String[]{user.getUsername()});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public boolean deleteUserData(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE username=?", new String[]{user.getUsername()});

        if (cursor.getCount() > 0) {


            int result = sqLiteDatabase.delete("user", "username= '" + user.getUsername() + "'", null);
            Log.d("result", String.valueOf(result));
            if (result > 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

}
