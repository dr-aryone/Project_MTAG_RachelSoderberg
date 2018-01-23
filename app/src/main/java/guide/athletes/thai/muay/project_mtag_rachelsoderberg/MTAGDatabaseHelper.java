package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

class MTAGDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mtag"; // Database name
    private static final int DB_VERSION = 1; // Database version

    MTAGDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION); // Call constructor and pass db name and version to it
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // When database is first called

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number increases

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number decreases
    }

    // private static void insertObject(SQLiteDatabase db, String name, String description, int resourceId) {}

    // private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
    //      if (oldVersion < 1) {
    //           Create empty object table
    //           Insert objects, each in a separate row
    //      }
    // }
}
