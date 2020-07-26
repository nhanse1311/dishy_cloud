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

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "User_Manager";
    private static final String TABLE_CHEF = "Chef_Manager";
    private static final String TABLE_USER = "User";
    private static final String USER_ID = "id";
    private static final String TOKEN = "access_token";
    private static final String USERNAME = "username";
    private static final String ID_RECIPE_LIKED = "id_recipe_liked";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+TABLE_USER+"( "+USER_ID+" INTEGER PRIMARY KEY, "+TOKEN+" TEXT,"+USERNAME+" TEXT )";
        String createChef = "CREATE TABLE "+TABLE_CHEF+"( "+ID_RECIPE_LIKED+" TEXT ,"+USERNAME+" TEXT)";
        sqLiteDatabase.execSQL(create);
        sqLiteDatabase.execSQL(createChef);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEF);
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

    public void addIdRecipeLiked(String id, String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_RECIPE_LIKED, id);
        values.put(USERNAME, username);
        database.insert(TABLE_CHEF,null,values);
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
    public String getUserId(){
        String userId = "";
        String selectQuery = "SELECT "+USER_ID+" FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                userId = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return userId;
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

    public List<String> getListLiked(String username) {
        List<String> noteList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT "+ID_RECIPE_LIKED+" FROM " + TABLE_CHEF+" WHERE "+USERNAME+" = '"+username+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                // Thêm vào danh sách.
                noteList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
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
