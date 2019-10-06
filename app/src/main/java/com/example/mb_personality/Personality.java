package com.example.mb_personality;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

public class Personality extends Activity {

    public static final String TAG = "Personality";

    public static final String EXTRA_PERSON_ID = "com.example.mb_personality.PERSON_ID";

    public static final int DEFAULT_VALUE = 0;
    public static final String DEFAULT_NAME = " Name ";

    public static final String TABLE_NAME = "person_table";
    public static final String C_NAME = "name";
    public static final String C_SE = "se";
    public static final String C_NE = "ne";
    public static final String C_TE = "te";
    public static final String C_TI = "ti";
    public static final String C_NI = "ni";
    public static final String C_SI = "si";
    public static final String C_FE = "fe";
    public static final String C_FI = "fi";
    public static final String C_ABSTRACTT = "abstract";
    public static final String C_AFFILIATIVE = "affiliative";
    public static final String C_INTEREST = "interest";
    public static final String C_DIRECT = "direct";
    public static final String C_INITIATING = "initiating";
    public static final String C_CONTROL = "control";
    public static final String C_CONCRETE = "concrete";
    public static final String C_PRAGMATIC = "pragmatic";
    public static final String C_SYSTEMATIC = "systematic";
    public static final String C_INFORMATIVE = "informative";
    public static final String C_RESPONDING = "responding";
    public static final String C_MOVEMENT = "movement";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_SE + " INTEGER," + C_NE + " INTEGER,"
            + C_TE + " INTEGER," + C_TI + " INTEGER,"
            + C_NI + " INTEGER," + C_SI + " INTEGER,"
            + C_FE + " INTEGER," + C_FI + " INTEGER,"
            + C_ABSTRACTT + " INTEGER," + C_AFFILIATIVE + " INTEGER,"
            + C_INTEREST + " INTEGER," + C_DIRECT + " INTEGER,"
            + C_INITIATING + " INTEGER," + C_CONTROL + " INTEGER,"
            + C_CONCRETE + " INTEGER," + C_PRAGMATIC + " INTEGER,"
            + C_SYSTEMATIC + " INTEGER," + C_INFORMATIVE + " INTEGER,"
            + C_RESPONDING + " INTEGER," + C_MOVEMENT + " INTEGER"
            + ")";

    private DatabaseHelper g_db;
    private String person_name;
    private int se;
    private int ne;
    private int te;
    private int ti;
    private int ni;
    private int si;
    private int fe;
    private int fi;
    private int abstractt;
    private int affiliative;
    private int interest;
    private int direct;
    private int initiating;
    private int control;
    private int concrete;
    private int pragmatic;
    private int systematic;
    private int informative;
    private int responding;
    private int movement;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality);

        g_db = new DatabaseHelper(this);

        String personId = getIntent().getExtras().getString(Personality.EXTRA_PERSON_ID);

        if (personId.equals("new")) {
            person_name = DEFAULT_NAME;
            se = ne = te = ti = ni = si = fe = fi = DEFAULT_VALUE;
            abstractt = affiliative = interest = direct = DEFAULT_VALUE;
            initiating = control = concrete = pragmatic = DEFAULT_VALUE;
            systematic = informative = responding = movement = DEFAULT_VALUE;
        } else {
            SQLiteDatabase db = g_db.getReadableDatabase();

            //get all personId columns
            Cursor cursor = db.query(TABLE_NAME,
                    null,
                    C_NAME + "=?",
                   new String[]{personId},
                    null,
                    null,
                    null,
                    null);

            if (cursor != null)
                cursor.moveToFirst();

            //update fields
            person_name = cursor.getString(cursor.getColumnIndex(C_NAME));
            se = cursor.getInt(cursor.getColumnIndex(C_SE));
            ne = cursor.getInt(cursor.getColumnIndex(C_NE));
            te = cursor.getInt(cursor.getColumnIndex(C_TE));
            ti = cursor.getInt(cursor.getColumnIndex(C_TI));
            ni = cursor.getInt(cursor.getColumnIndex(C_NI));
            si = cursor.getInt(cursor.getColumnIndex(C_SI));
            fe = cursor.getInt(cursor.getColumnIndex(C_FE));
            fi = cursor.getInt(cursor.getColumnIndex(C_FI));
            abstractt = cursor.getInt(cursor.getColumnIndex(C_ABSTRACTT));
            affiliative = cursor.getInt(cursor.getColumnIndex(C_AFFILIATIVE));
            interest = cursor.getInt(cursor.getColumnIndex(C_INTEREST));
            direct = cursor.getInt(cursor.getColumnIndex(C_DIRECT));
            initiating = cursor.getInt(cursor.getColumnIndex(C_INITIATING));
            control = cursor.getInt(cursor.getColumnIndex(C_CONTROL));
            concrete = cursor.getInt(cursor.getColumnIndex(C_CONCRETE));
            pragmatic = cursor.getInt(cursor.getColumnIndex(C_PRAGMATIC));
            systematic = cursor.getInt(cursor.getColumnIndex(C_SYSTEMATIC));
            informative = cursor.getInt(cursor.getColumnIndex(C_INFORMATIVE));
            responding = cursor.getInt(cursor.getColumnIndex(C_RESPONDING));
            movement = cursor.getInt(cursor.getColumnIndex(C_MOVEMENT));

            cursor.close();
        }

        //populate all fields
        setTextView((TextView) findViewById(R.id.person_name), person_name);
        setTextView((TextView) findViewById(R.id.tv_se), se);
        setTextView((TextView) findViewById(R.id.tv_ne), ne);
        setTextView((TextView) findViewById(R.id.tv_te), te);
        setTextView((TextView) findViewById(R.id.tv_ti), ti);
        setTextView((TextView) findViewById(R.id.tv_ni), ni);
        setTextView((TextView) findViewById(R.id.tv_si), si);
        setTextView((TextView) findViewById(R.id.tv_fe), fe);
        setTextView((TextView) findViewById(R.id.tv_fi), fi);
        setTextView((TextView) findViewById(R.id.tv_abstractt), abstractt);
        setTextView((TextView) findViewById(R.id.tv_affiliative), affiliative);
        setTextView((TextView) findViewById(R.id.tv_interest), interest);
        setTextView((TextView) findViewById(R.id.tv_direct), direct);
        setTextView((TextView) findViewById(R.id.tv_initiating), initiating);
        setTextView((TextView) findViewById(R.id.tv_control), control);
        setTextView((TextView) findViewById(R.id.tv_concrete), concrete);
        setTextView((TextView) findViewById(R.id.tv_pragmatic), pragmatic);
        setTextView((TextView) findViewById(R.id.tv_systematic), systematic);
        setTextView((TextView) findViewById(R.id.tv_informative), informative);
        setTextView((TextView) findViewById(R.id.tv_responding), responding);
        setTextView((TextView) findViewById(R.id.tv_movement), movement);

        UpdateDomTraits();
    }

    public void savePersonality(View v) {
        getTextViewValues();

        SQLiteDatabase db = g_db.getWritableDatabase();
        ContentValues values = new ContentValues(21);

        values.put(C_NAME, person_name);
        values.put(C_SE, se);
        values.put(C_NE, ne);
        values.put(C_TE, te);
        values.put(C_TI, ti);
        values.put(C_NI, ni);
        values.put(C_SI, si);
        values.put(C_FE, fe);
        values.put(C_FI, fi);
        values.put(C_ABSTRACTT, abstractt);
        values.put(C_AFFILIATIVE, affiliative);
        values.put(C_INTEREST, interest);
        values.put(C_DIRECT, direct);
        values.put(C_INITIATING, initiating);
        values.put(C_CONTROL, control);
        values.put(C_CONCRETE, concrete);
        values.put(C_PRAGMATIC, pragmatic);
        values.put(C_SYSTEMATIC, systematic);
        values.put(C_INFORMATIVE, informative);
        values.put(C_RESPONDING, responding);
        values.put(C_MOVEMENT, movement);

        int exist = db.update(TABLE_NAME, values, C_NAME + " = ?", new String[]{person_name});
        if (exist == 0) {
            db.insertWithOnConflict(TABLE_NAME,null, values, CONFLICT_IGNORE);
        }
        Log.i(TAG, "save return is " + exist + " for " + person_name);
        finish();
    }

    public void deletePersonality(View v) {
        SQLiteDatabase db = g_db.getWritableDatabase();
        int rv = db.delete(TABLE_NAME, C_NAME + " = ?", new String[]{person_name});
        Log.i(TAG, "delete return is " + rv + " for " + person_name);
        finish();
    }

    private void getTextViewValues() {
        person_name = getTextViewString((TextView) findViewById(R.id.person_name));
        se = getTextViewInt((TextView) findViewById(R.id.tv_se));
        ne = getTextViewInt((TextView) findViewById(R.id.tv_ne));
        te = getTextViewInt((TextView) findViewById(R.id.tv_te));
        ti = getTextViewInt((TextView) findViewById(R.id.tv_ti));
        ni = getTextViewInt((TextView) findViewById(R.id.tv_ni));
        si = getTextViewInt((TextView) findViewById(R.id.tv_si));
        fe = getTextViewInt((TextView) findViewById(R.id.tv_fe));
        fi = getTextViewInt((TextView) findViewById(R.id.tv_fi));
        abstractt = getTextViewInt((TextView) findViewById(R.id.tv_abstractt));
        affiliative = getTextViewInt((TextView) findViewById(R.id.tv_affiliative));
        interest = getTextViewInt((TextView) findViewById(R.id.tv_interest));
        direct = getTextViewInt((TextView) findViewById(R.id.tv_direct));
        initiating = getTextViewInt((TextView) findViewById(R.id.tv_initiating));
        control = getTextViewInt((TextView) findViewById(R.id.tv_control));
        concrete = getTextViewInt((TextView) findViewById(R.id.tv_concrete));
        pragmatic = getTextViewInt((TextView) findViewById(R.id.tv_pragmatic));
        systematic = getTextViewInt((TextView) findViewById(R.id.tv_systematic));
        informative = getTextViewInt((TextView) findViewById(R.id.tv_informative));
        responding = getTextViewInt((TextView) findViewById(R.id.tv_responding));
        movement = getTextViewInt((TextView) findViewById(R.id.tv_movement));
    }

    public void increment(View v) {
        switch(v.getId()) {
            case (R.id.se_plus):
                updateIncrement((TextView) findViewById(R.id.tv_se));
                break;
            case (R.id.ni_plus):
                updateIncrement((TextView) findViewById(R.id.tv_ni));
                break;
            case (R.id.ne_plus):
                updateIncrement((TextView) findViewById(R.id.tv_ne));
                break;
            case (R.id.si_plus):
                updateIncrement((TextView) findViewById(R.id.tv_si));
                break;
            case (R.id.te_plus):
                updateIncrement((TextView) findViewById(R.id.tv_te));
                break;
            case (R.id.fe_plus):
                updateIncrement((TextView) findViewById(R.id.tv_fe));
                break;
            case (R.id.ti_plus):
                updateIncrement((TextView) findViewById(R.id.tv_ti));
                break;
            case (R.id.fi_plus):
                updateIncrement((TextView) findViewById(R.id.tv_fi));
                break;
            case (R.id.abstractt_plus):
                updateIncrement((TextView) findViewById(R.id.tv_abstractt));
                break;
            case (R.id.affiliative_plus):
                updateIncrement((TextView) findViewById(R.id.tv_affiliative));
                break;
            case (R.id.interest_plus):
                updateIncrement((TextView) findViewById(R.id.tv_interest));
                break;
            case (R.id.direct_plus):
                updateIncrement((TextView) findViewById(R.id.tv_direct));
                break;
            case (R.id.initiating_plus):
                updateIncrement((TextView) findViewById(R.id.tv_initiating));
                break;
            case (R.id.control_plus):
                updateIncrement((TextView) findViewById(R.id.tv_control));
                break;
            case (R.id.concrete_plus):
                updateIncrement((TextView) findViewById(R.id.tv_concrete));
                break;
            case (R.id.pragmatic_plus):
                updateIncrement((TextView) findViewById(R.id.tv_pragmatic));
                break;
            case (R.id.systematic_plus):
                updateIncrement((TextView) findViewById(R.id.tv_systematic));
                break;
            case (R.id.informative_plus):
                updateIncrement((TextView) findViewById(R.id.tv_informative));
                break;
            case (R.id.responding_plus):
                updateIncrement((TextView) findViewById(R.id.tv_responding));
                break;
            case (R.id.movement_plus):
                updateIncrement((TextView) findViewById(R.id.tv_movement));
                break;
            default:
                Log.i("Increment", "hit default...");
        }
    }

    public void updateIncrement(TextView tv) {
        String svalue = tv.getText().toString();

        if (!svalue.equals("")) {
            int num = Integer.parseInt(svalue);
            num+=1;
            tv.setText("" + num);
            UpdateDomTraits();
        }
    }

    public void decrement(View v) {
        switch(v.getId()) {
            case (R.id.se_min):
                updateDecrement((TextView) findViewById(R.id.tv_se));
                break;
            case (R.id.ni_min):
                updateDecrement((TextView) findViewById(R.id.tv_ni));
                break;
            case (R.id.ne_min):
                updateDecrement((TextView) findViewById(R.id.tv_ne));
                break;
            case (R.id.si_min):
                updateDecrement((TextView) findViewById(R.id.tv_si));
                break;
            case (R.id.te_min):
                updateDecrement((TextView) findViewById(R.id.tv_te));
                break;
            case (R.id.fe_min):
                updateDecrement((TextView) findViewById(R.id.tv_fe));
                break;
            case (R.id.ti_min):
                updateDecrement((TextView) findViewById(R.id.tv_ti));
                break;
            case (R.id.fi_min):
                updateDecrement((TextView) findViewById(R.id.tv_fi));
                break;
            case (R.id.abstractt_min):
                updateDecrement((TextView) findViewById(R.id.tv_abstractt));
                break;
            case (R.id.affiliative_min):
                updateDecrement((TextView) findViewById(R.id.tv_affiliative));
                break;
            case (R.id.interest_min):
                updateDecrement((TextView) findViewById(R.id.tv_interest));
                break;
            case (R.id.direct_min):
                updateDecrement((TextView) findViewById(R.id.tv_direct));
                break;
            case (R.id.initiating_min):
                updateDecrement((TextView) findViewById(R.id.tv_initiating));
                break;
            case (R.id.control_min):
                updateDecrement((TextView) findViewById(R.id.tv_control));
                break;
            case (R.id.concrete_min):
                updateDecrement((TextView) findViewById(R.id.tv_concrete));
                break;
            case (R.id.pragmatic_min):
                updateDecrement((TextView) findViewById(R.id.tv_pragmatic));
                break;
            case (R.id.systematic_min):
                updateDecrement((TextView) findViewById(R.id.tv_systematic));
                break;
            case (R.id.informative_min):
                updateDecrement((TextView) findViewById(R.id.tv_informative));
                break;
            case (R.id.responding_min):
                updateDecrement((TextView) findViewById(R.id.tv_responding));
                break;
            case (R.id.movement_min):
                updateDecrement((TextView) findViewById(R.id.tv_movement));
                break;
            default:
                Log.i("Decrement", "hit default...");
        }
    }

    public void updateDecrement(TextView tv) {
        String svalue = tv.getText().toString();
        if (!svalue.equals("")) {
            int num = Integer.parseInt(svalue);
            if (num > 0) {
                num-=1;
                tv.setText("" + num);
                UpdateDomTraits();
            }
        }
    }

    private void setTextView(TextView tv, int stat) {
        tv.setText("" + stat);
    }
    private void setTextView(TextView tv, String str) {
        tv.setText("" + str);
    }
    private String getTextViewString(TextView tv) {
        return tv.getText().toString();
    }
    private int getTextViewInt(TextView tv) {
        return Integer.parseInt(tv.getText().toString());
    }

    private void UpdateDomTraits() {
        String domTrait = "";
        int etrait, itrait;

        //sensing
        etrait = getTextViewInt((TextView) findViewById(R.id.tv_se));
        itrait = getTextViewInt((TextView) findViewById(R.id.tv_si));
        if (etrait > itrait) {
            domTrait += "Se, ";
        } else {
            domTrait += "Si, ";
        }

        //intuition
        etrait = getTextViewInt((TextView) findViewById(R.id.tv_ne));
        itrait = getTextViewInt((TextView) findViewById(R.id.tv_ni));
        if (etrait > itrait) {
            domTrait += "Ne, ";
        } else {
            domTrait += "Ni, ";
        }

        //feeling
        etrait = getTextViewInt((TextView) findViewById(R.id.tv_fe));
        itrait = getTextViewInt((TextView) findViewById(R.id.tv_fi));
        if (etrait > itrait) {
            domTrait += "Fe, ";
        } else {
            domTrait += "Fi, ";
        }

        //thinking
        etrait = getTextViewInt((TextView) findViewById(R.id.tv_te));
        itrait = getTextViewInt((TextView) findViewById(R.id.tv_ti));
        if (etrait > itrait) {
            domTrait += "Te";
        } else {
            domTrait += "Ti";
        }

        setTextView((TextView) findViewById(R.id.tv_dom_traits), domTrait);
    }
}
