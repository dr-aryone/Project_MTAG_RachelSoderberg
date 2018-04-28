package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ComboSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_combos";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_COMBOS = "combos";

    public static final String KEY_ID = "id";
    public static final String KEY_COMBO_NAME = "comboName";
    public static final String KEY_COMBO = "combo";

    //public int combosCount = 0;

    public static final String[] COLUMNS = {KEY_ID, KEY_COMBO_NAME, KEY_COMBO};

    public ComboSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Create Combos table
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

        //++combosCount;
        // Close
        db.close();
    }
}