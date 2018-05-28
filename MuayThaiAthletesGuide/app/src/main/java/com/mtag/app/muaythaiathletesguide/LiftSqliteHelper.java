package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LiftSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtag_lifts";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_LIFTS = "lifts";

    public static final String KEY_ID = "id";
    public static final String KEY_LIFT_NAME = "liftName";
    public static final String KEY_LIFT_DESC = "liftDesc";

    public static final String[] COLUMNS = {KEY_ID, KEY_LIFT_NAME, KEY_LIFT_DESC};

    public LiftSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_LIFTS = "CREATE TABLE lifts ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "liftName TEXT, " + "liftDesc TEXT )";

        db.execSQL(SQL_TABLE_LIFTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lifts");
        this.onCreate(db);
    }

    public void addLift(Lift lift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIFT_NAME, lift.getLiftName());
        values.put(KEY_LIFT_DESC, lift.getLiftDesc());

        db.insert(TABLE_LIFTS, null, values);
        db.close();
    }
}
