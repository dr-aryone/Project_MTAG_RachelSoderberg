package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String SQL_TABLE_COMBOS = "CREATE TABLE combos ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "comboName TEXT, " +
                "combo TEXT )";

        db.execSQL(SQL_TABLE_COMBOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS combos");
        this.onCreate(db);
    }

    public void addCombo(Combo combo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COMBO_NAME, combo.getComboName());
        values.put(KEY_COMBO, combo.getCombo());

        db.insert(TABLE_COMBOS,null, values); // Key/value -> keys = column names/ values = column values)
        db.close();
    }
}