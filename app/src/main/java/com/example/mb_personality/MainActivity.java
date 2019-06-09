package com.example.mb_personality;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    public static final String TAG = "Personality_Main";
    private DatabaseHelper g_db;
    private DbCursorAdapter adapter;
    private Cursor personList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        g_db = new DatabaseHelper(this);

        personList = g_db.getAllPersonalities(g_db);
        adapter = new DbCursorAdapter(this, R.layout.list_row, personList, 0);

        ListView listview = (ListView) findViewById(R.id.lv_people);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        personList = g_db.getAllPersonalities(g_db);
        ListView listview = (ListView) findViewById(R.id.lv_people);
        adapter = new DbCursorAdapter(this, R.layout.list_row, personList, 0);
        listview.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Cursor c = (Cursor) l.getItemAtPosition(position);
        String s = c.getString(c.getColumnIndex(Personality.C_NAME));
        Log.i(TAG, "clicked string is " + s);
        Intent intent = new Intent(this, Personality.class);
        intent.putExtra(Personality.EXTRA_PERSON_ID, c.getString(c.getColumnIndex(Personality.C_NAME)));
        startActivity(intent);
    }

    public void createNew(View v) {
        Intent intent = new Intent(this, Personality.class);
        intent.putExtra(Personality.EXTRA_PERSON_ID, "new");
        startActivity(intent);
    }

}
