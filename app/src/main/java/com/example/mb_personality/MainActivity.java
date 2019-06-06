package com.example.mb_personality;

import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    //private ArrayList<HashMap<String, String>> personList = new ArrayList<>();
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

        //TODO BETTER NAME for listView1
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        //NOTE: it seems to fail due to no _id, which may be needed for CursorAdapter... look into this...

        //NOTE:
        // both helpful for setting up Database.
        // http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
        // http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        HashMap<String, String> hashMap = (HashMap<String, String>) l.getItemAtPosition(position);
        Intent intent = new Intent(this, Personality.class);
        intent.putExtra(Personality.EXTRA_PERSON_ID, hashMap.get("name"));
        startActivity(intent);
    }

    public void createNew(View v) {
        Intent intent = new Intent(this, Personality.class);
        intent.putExtra(Personality.EXTRA_PERSON_ID, "new");
        startActivity(intent);
    }

}
