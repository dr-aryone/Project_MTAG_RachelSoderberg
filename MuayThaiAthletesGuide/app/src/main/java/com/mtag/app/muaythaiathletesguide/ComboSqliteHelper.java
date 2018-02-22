package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rachel on 2/20/2018.
 */

public class ComboSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_combos";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_COMBOS = "combos";

    // TABLE USERS COLUMNS
    // ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    public static final String KEY_COMBO_NAME = "comboName";
    public static final String KEY_COMBO = "combo";
    // SQL for creating users table
    public static final String SQL_TABLE_COMBOS = " CREATE TABLE " + TABLE_COMBOS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_COMBO_NAME + " TEXT, "
            + KEY_COMBO + " TEXT, "
            + " ) ";

    public ComboSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create Table when onCreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_COMBOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_COMBOS);
    }

    // Add users to combo table
    public void addCombo(Combo combo) {

        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // Create content values to insert
        ContentValues values = new ContentValues();

        // Put combo name and combo in @values
        values.put(KEY_COMBO_NAME, combo.comboName);
        values.put(KEY_COMBO, combo.combo);

        // Insert row
        long todo_id = db.insert(TABLE_COMBOS, null, values);
    }
}
