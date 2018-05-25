package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PortalSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_portal";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PORTAL = "portal";

    public static final String KEY_ID = "id";
    public static final String KEY_IN_NAME = "inName";
    public static final String KEY_IN_DESC = "inDesc";

    //public static final String[] COLUMNS = {KEY_ID, KEY_IN_NAME, KEY_IN_DESC};

    public static final String SQL_TABLE_PORTAL = " CREATE TABLE " + TABLE_PORTAL
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_IN_NAME + " TEXT, "
            + KEY_IN_DESC + " TEXT"
            + " ) ";

    public PortalSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create Table when onCreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_PORTAL);
    }

    /*public void onCreate(SQLiteDatabase db) {
        // Create Combos table
        String SQL_TABLE_PORTAL = "CREATE TABLE portal ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "inName TEXT, " +
                "inDesc TEXT )";

        // Create portal table
        db.execSQL(SQL_TABLE_PORTAL);
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PORTAL);
    }

    /*@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete old table
        db.execSQL("DROP TABLE IF EXISTS portal");

        // Create fresh table
        this.onCreate(db);
    }*/

    public boolean isNameExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PORTAL,// Selecting Table
                new String[]{KEY_ID, KEY_IN_NAME, KEY_IN_DESC},
                KEY_IN_NAME + "=?",
                new String[]{name},//Where clause
                null, null, null);

        return cursor != null && cursor.moveToFirst();
    }

    public void addPortal(Portal portal) {
        // Get writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_IN_NAME, portal.inName);
        values.put(KEY_IN_DESC, portal.inDesc);

        long todo_id = db.insert(TABLE_PORTAL, null, values);

        // Insert(Table, nullColumnHack, Key/value)
        //db.insert(TABLE_PORTAL,null, values); // Key/value -> keys = column names/ values = column values)
        //db.close();
    }
}
