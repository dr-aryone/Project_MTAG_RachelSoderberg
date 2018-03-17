package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rachel on 3/5/2018.
 */

public class MartialSqliteHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "mtag_martial";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MARTIAL = "martial";

    public static final String KEY_ID = "id";
    public static final String KEY_MARTIAL_NAME = "martialName";
    public static final String KEY_MARTIAL_DESC = "martialDesc";
    public static final String KEY_MARTIAL_TIME_LIMIT = "martialTimeLimit";

    public static final String[] COLUMNS = {KEY_ID, KEY_MARTIAL_NAME, KEY_MARTIAL_DESC, KEY_MARTIAL_TIME_LIMIT};

    public MartialSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_MARTIAL = "CREATE TABLE martial ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "martialName TEXT, " + "martialDesc TEXT, " + "martialTimeLimit )";

        // Create lifts table
        db.execSQL(SQL_TABLE_MARTIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete old table
        db.execSQL("DROP TABLE IF EXISTS martial");

        // Create fresh table
        this.onCreate(db);
    }

    public void addMartial(Martial martial) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MARTIAL_NAME, martial.getMartialName());
        values.put(KEY_MARTIAL_DESC, martial.getMartialDesc());
        values.put(KEY_MARTIAL_TIME_LIMIT, martial.getMartialTimeLimit());

        db.insert(TABLE_MARTIAL, null, values);

        db.close();
    }
}
