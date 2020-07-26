package com.example.dishycloud.sqlites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dishycloud.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "User_Manager";
    private static final String TABLE_USER = "User";
    private static final String USER_ID = "id";
    private static final String TOKEN = "access_token";
    private static final String USERNAME = "username";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+TABLE_USER+"( "+USER_ID+" INTEGER PRIMARY KEY, "+TOKEN+" TEXT,"+USERNAME+" TEXT )";

        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addNote(User user, String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOKEN, user.getAccess_token());
        values.put(USERNAME, username);
        database.insert(TABLE_USER,null,values);
        database.close();
    }

    public String getUsername() {
        // Select All Query
        String username = "";
        String selectQuery = "SELECT "+USERNAME+" FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                username = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return  username;
    }

    public String getToken() {
        // Select All Query
        String token = "";
        String selectQuery = "SELECT "+TOKEN+" FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                token = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return  token;
    }

    public int updateNote(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOKEN,user.getAccess_token());
        return database.update(TABLE_USER,values,TOKEN + "=?",new String[]{String.valueOf(user.getAccess_token())});
    }

    public void deleteToken(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+TABLE_USER);
        database.close();
    }
}
