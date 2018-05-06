package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PortalSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_portal";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PORTAL = "portal";

    public static final String KEY_ID = "id";
    public static final String KEY_IN_NAME = "inName";
    public static final String KEY_IN_DESC = "inDesc";

    public static final String[] COLUMNS = {KEY_ID, KEY_IN_NAME, KEY_IN_DESC};

    public PortalSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Create Combos table
        String SQL_TABLE_PORTAL = "CREATE TABLE portal ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "inName TEXT, " +
                "inDesc TEXT )";

        // Create portal table
        db.execSQL(SQL_TABLE_PORTAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete old table
        db.execSQL("DROP TABLE IF EXISTS portal");

        // Create fresh table
        this.onCreate(db);
    }

    public void addPortal(Portal portal) {
        // For Logcat
        Log.d("addPortal", portal.toString());

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_IN_NAME, portal.getInName());
        values.put(KEY_IN_DESC, portal.getInDesc());

        // Insert(Table, nullColumnHack, Key/value)
        db.insert(TABLE_PORTAL,null, values); // Key/value -> keys = column names/ values = column values)
        db.close();
    }
}
