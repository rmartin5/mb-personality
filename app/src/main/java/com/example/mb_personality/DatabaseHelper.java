package com.example.mb_personality;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


        String selectQuery = "SELECT _id, " + Personality.C_NAME + " FROM " + Personality.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        /*
        cursor.moveToFirst();
        String s = cursor.getString(cursor.getColumnIndex(Personality.C_NAME));
        Log.i("TEST","s is " + s);
        while(cursor.moveToNext()) {
            s = cursor.getString(cursor.getColumnIndex(Personality.C_NAME));
            Log.i("TEST","s is " + s);
        }

        String countQuery = "SELECT  * FROM " + Personality.TABLE_NAME;
        Cursor cursor2 = db.rawQuery(selectQuery, null);
        cursor2.moveToFirst();
        Log.i("TEST2","s2 is " + cursor2.getString(cursor2.getColumnIndex("_id")) + " and " +
                cursor2.getString(cursor2.getColumnIndex(Personality.C_NAME)));
        while(cursor2.moveToNext()) {
            Log.i("TEST2","s2 is " + cursor2.getString(cursor2.getColumnIndex("_id")) + " and " +
                    cursor2.getString(cursor2.getColumnIndex(Personality.C_NAME)));
        }*/

        return cursor;

        /*
        SQLiteDatabase db = g_db.getWritableDatabase();
        Cursor cursor = db.query(Personality.TABLE_NAME,
                new String[]{"_id", Personality.C_NAME},
                null,
                null,
                null,
                null,
                null);

        return cursor;

        String selectQuery = "SELECT " + Personality.C_NAME + " FROM " + Personality.TABLE_NAME +
                " ORDER BY " + Personality.C_NAME + " ASC";

        SQLiteDatabase db = g_db.getWritableDatabase();

        return db.rawQuery(selectQuery, null);*/
    }
}
