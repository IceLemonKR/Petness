package com.example.petness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private final String TAG = "MySQLiteOpenHelper";

    public DataBaseHelper(@Nullable String latitude, @Nullable String longitude) {
        super(latitude, longitude);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table student (_id integer primary key autoincrement, name text, age integer, address text)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql="drop table if exists student";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static class MyDBHandler {

        private final String TAG = "MyDBHandler";

        SQLiteOpenHelper mHelper = null;
        SQLiteDatabase mDB = null;

        public MyDBHandler(String latitude, String longitude) {
            mHelper = new DataBaseHelper(latitude, longitude);
        }

        public static MyDBHandler open(String latitude, String longitude) {
            return new MyDBHandler(latitude, longitude);
        }

        public Cursor select()
        {
            mDB = mHelper.getReadableDatabase();
            Cursor c = mDB.query("student", null, null, null, null, null, null);
            return c;
        }

        public void insert(String latitude, String longitude) {

            Log.d(TAG, "insert");

            mDB = mHelper.getWritableDatabase();

            ContentValues value = new ContentValues();
            value.put("latitude", latitude);
            value.put("longitude", longitude);

            mDB.insert("Location", null, value);

        }

        public void delete(String name)
        {
            Log.d(TAG, "delete");
            mDB = mHelper.getWritableDatabase();
            mDB.delete("Location", "latitude=?", new String[]{});
        }

        public void close() {
            mHelper.close();
        }
    }
}