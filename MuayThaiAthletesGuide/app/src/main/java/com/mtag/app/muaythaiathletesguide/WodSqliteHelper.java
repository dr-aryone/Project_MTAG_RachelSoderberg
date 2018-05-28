package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WodSqliteHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "mtag_wods";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_WODS = "wods";

    public static final String KEY_ID = "id";
    public static final String KEY_WOD_NAME = "wodName";
    public static final String KEY_WOD_DESC = "wodDesc";
    public static final String KEY_WOD_TIME_LIMIT = "wodTimeLimit";

    public static final String[] COLUMNS = {KEY_ID, KEY_WOD_NAME, KEY_WOD_DESC, KEY_WOD_TIME_LIMIT};

    public WodSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_WODS = "CREATE TABLE wods ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "wodName TEXT, " + "wodDesc TEXT, " + "wodTimeLimit )";

        db.execSQL(SQL_TABLE_WODS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lifts");
        this.onCreate(db);
    }

    public void addWod(Wod wod) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WOD_NAME, wod.getWodName());
        values.put(KEY_WOD_DESC, wod.getWodDesc());
        values.put(KEY_WOD_TIME_LIMIT, wod.getWodTimeLimit());

        db.insert(TABLE_WODS, null, values);

        db.close();
    }
}
