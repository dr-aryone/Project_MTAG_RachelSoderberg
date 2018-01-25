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
        updateDatabase(db, 0, DB_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number increases
        updateDatabase(db, oldVersion, newVersion);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number decreases
        updateDatabase(db, oldVersion, newVersion);
    }

    private static void insertUser(SQLiteDatabase db, String username, String email, String firstname, String lastname, String country) {
        ContentValues userValues = new ContentValues();
        userValues.put("USERNAME", username);
        userValues.put("EMAIL", email);
        userValues.put("FIRSTNAME", firstname);
        userValues.put("LASTNAME", lastname);
        userValues.put("COUNTRY", country);
        db.insert("USER", null, userValues);
    }

    private static void insertAccount(SQLiteDatabase db, String username, String password) {
        ContentValues accountValues = new ContentValues();
        accountValues.put("USERNAME", username);
        accountValues.put("PASSWORD", password);
    }

     private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
          if (oldVersion < 1) {
              // Create User table
              db.execSQL("CREATE TABLE USER ("
              + "_id PRIMARY KEY AUTOINCREMENT, "
              + "USERNAME TEXT, "
              + "EMAIL TEXT, "
              + "FIRSTNAME TEXT, "
              + "LASTNAME TEXT, "
              + "COUNTRY TEXT);");
              // Populate User table data (for testing)
              insertUser(db, "CL1FF72", "cliffsemail@hotmail.com", "Cliff", "Cliffton", "USA");

              // Create Account info table
              db.execSQL("CREATE TABLE ACCOUNT ("
              + "_id PRIMARY KEY AUTOINCREMENT, "
              + "USERNAME TEXT, "
              + "PASSWORD TEXT);");
              // Populate Account table data (for testing)
              insertAccount(db, "CL1FF72", "123abc");
          }
     }
}
