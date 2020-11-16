package com.example.petness;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class DataBaseHelper extends Activity{
    private DBManager mDBManager;
    private SQLiteDatabase mDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.MainActivity);
        mDBManager = new DBManager(this);
        mDBManager.getReadableDatabase();
        mDBManager.getWritableDatabase();
        mDBManager.close();
    }

}