package com.example.mb_personality;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class DbCursorAdapter extends ResourceCursorAdapter {
    public DbCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String id = cursor.getString(0);
        Log.i("DBCursorAdapter", "the output is " + id + " and " + cursor.getColumnIndex(Personality.C_NAME) +
                " and " + cursor.getString(cursor.getColumnIndex(Personality.C_NAME)));
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(cursor.getString(cursor.getColumnIndex(Personality.C_NAME)));
    }
}