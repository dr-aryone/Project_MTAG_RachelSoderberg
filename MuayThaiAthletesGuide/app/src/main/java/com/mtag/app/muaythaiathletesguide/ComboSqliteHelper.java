package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rachel on 2/20/2018.
 */

public class ComboSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_combos";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_COMBOS = "combos";

    public static final String KEY_ID = "id";
    public static final String KEY_COMBO_NAME = "comboName";
    public static final String KEY_COMBO = "combo";

    public static final String[] COLUMNS = {KEY_ID, KEY_COMBO_NAME, KEY_COMBO};

    public ComboSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Create Combos table
        /*String SQL_TABLE_COMBOS = " CREATE TABLE " + TABLE_COMBOS
                + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_COMBO_NAME + " TEXT, "
                + KEY_COMBO + " TEXT, "
                + " ) ";*/
        String SQL_TABLE_COMBOS = "CREATE TABLE combos ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "comboName TEXT, " +
                "combo TEXT )";

        // Create combos table
        db.execSQL(SQL_TABLE_COMBOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete old table
        db.execSQL("DROP TABLE IF EXISTS combos");

        // Create fresh table
        this.onCreate(db);
    }

    public void addCombo(Combo combo) {
        // For Logcat
        Log.d("addCombo", combo.toString());

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_COMBO_NAME, combo.getComboName());
        values.put(KEY_COMBO, combo.getCombo());

        // Insert
        db.insert(TABLE_COMBOS, // Table
                null, // nullColumnHack
                values); // Key/value -> keys = column names/ values = column values)

        // Close
        db.close();
    }

    public Combo getComboName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        Cursor cursor = db.query(TABLE_COMBOS, // Table
                COLUMNS, // Column Names
                " id = ?", // Selections
                new String[] { String.valueOf(id) }, // Selections args
                null, // Group by
                null, // Having
                null, // Order by
                null); // Limit
        if (cursor!= null)
            cursor.moveToFirst();

        // Build Combo object
        Combo combo = new Combo();
        combo.setId(Integer.parseInt(cursor.getString(0)));
        combo.setComboName(cursor.getString(1));
        combo.setCombo(cursor.getString(2));

        // For Logcat
        Log.d("getCombo("+id+")", combo.toString());

        return combo;
    }

    public List<Combo> getAllCombos() {
        List<Combo> combos = new LinkedList<Combo>();

        // Build query
        String query = "SELECT * FROM " + TABLE_COMBOS;

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Go over each row, build combo and add it to list
        Combo combo = null;
        if (cursor.moveToFirst()) {
            do {
                combo = new Combo();
                combo.setId(Integer.parseInt(cursor.getString(0)));
                combo.setComboName(cursor.getString(1));
                combo.setCombo(cursor.getString(2));

                // Add combo to combos
                combos.add(combo);
            } while (cursor.moveToNext());
        }
        Log.d("getAllCombos()", combos.toString());

        return combos;
    }

    // Update combo
    public int updateCombo(Combo combo) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/values
        ContentValues values = new ContentValues();
        values.put("comboName", combo.getComboName());
        values.put("combo", combo.getCombo());

        // Update row
        int i = db.update(TABLE_COMBOS, // Table
                values, // Column/value
                KEY_ID+" = ?", // Selections
                new String[] { String.valueOf(combo.getId()) }); // Selection args

        // Close
        db.close();

        return i;
    }

    // Delete combo
    public void deleteCombo(Combo combo) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete
        db.delete(TABLE_COMBOS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(combo.getId()) });

        db.close();

        Log.d("deleteCombo", combo.toString());
    }
}

/*
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
}*/
