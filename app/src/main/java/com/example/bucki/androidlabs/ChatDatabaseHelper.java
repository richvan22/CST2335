package com.example.bucki.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Richy on 2017-02-13.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Chats.db";
    public static int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "ChatMessage";
    public static final String KEY_ID = "_id"; //col1
    public static final String KEY_MSG = "message"; //col2

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "onCreate" );
        String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MSG + " TEXT)";
        Log.i("ChatDatabaseHelper", "Testing: " + CREATE_CHAT_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "onUpgrade version " + oldVersion +" to new database version: " +  newVersion );
    }

    public void insertData(String msg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MSG, msg);
        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        Log.i("ChatDatabaseHelper", "insert data result: " + insertResult );
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
