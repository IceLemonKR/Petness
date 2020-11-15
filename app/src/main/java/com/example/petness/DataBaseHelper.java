package com.example.petness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class DataBaseHelper extends AppCompatActivity {
    public static final String DATABASE_NAME = "Petness.db";
    public static final String TABLE_NAME = "Location";
    public static final String COL_1 = "Latitude";
    public static final String COL_2 = "longitude";

    private static DataBaseHelper INSTANCE;
    private static SQLiteDatabase mDatabase;

    public static DataBaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseHelper(context);
            mDatabase = INSTANCE.getWritableDatabase();
        }

        return INSTANCE;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    protected void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" +
                "date DEFAULT (date('now', 'localtime')) PRIMARY KEY, " +
                "datetime DEFAULT (datetime('now', 'localtime'))," +
                "depressStatus int(1) " +
                ")"
        );
    }
}