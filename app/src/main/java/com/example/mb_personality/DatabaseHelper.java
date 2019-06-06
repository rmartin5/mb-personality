package com.example.mb_personality;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "person_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Personality.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + Personality.TABLE_NAME);
        onCreate(db);
    }


    public Cursor getAllPersonalities(DatabaseHelper g_db) {
        String selectQuery = "SELECT " + Personality.C_NAME + " FROM " + Personality.TABLE_NAME +
                " ORDER BY " + Personality.C_NAME + " ASC";

        SQLiteDatabase db = g_db.getWritableDatabase();
        return db.rawQuery(selectQuery, null);
    }
}
