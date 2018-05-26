package com.mtag.app.muaythaiathletesguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class PortalSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "mtag_portal";

    public PortalSQLiteHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PORTAL = "CREATE TABLE " + Portal.TABLE_PORTAL + "("
                + Portal.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Portal.KEY_IN_NAME + " TEXT, "
                + Portal.KEY_IN_DESC + " TEXT )";
        db.execSQL(CREATE_TABLE_PORTAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Portal.TABLE_PORTAL);
        onCreate(db);
    }
}
