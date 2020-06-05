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

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User_Manager";
    private static final String TABLE_USER = "User";
    private static final String USER_ID = "id";
    private static final String TOKEN = "access_token";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+TABLE_USER+"( "+USER_ID+" INTEGER PRIMARY KEY, "+TOKEN+" TEXT )";

        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addNote(User user) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TOKEN, user.getAccess_token());
        database.insert(TABLE_USER,null,values);
        database.close();
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

    public void deleteNote(User note){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_USER,TOKEN+ "=?",new String[]{String.valueOf(note.getAccess_token())});
        database.close();
    }
}
