package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

// Resources: https://www.loopwiki.com/beginner/android-login-register-sqlite-database-tutorial/

class MTAGDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mtag"; // Database name
    private static final int DB_VERSION = 1; // Database version

    public static final String ACCOUNT_TABLE_NAME = "account";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Create Account table
    private static final String SQL_ACCOUNT_TABLE =
           "CREATE TABLE " + ACCOUNT_TABLE_NAME + " (" +
                   COLUMN_ID + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
                   COLUMN_USERNAME + "TEXT" + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT " + ")";

    MTAGDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // When database is first called
        db.execSQL(ACCOUNT_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number increases
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
    }
/*
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update database when version number decreases
        updateDatabase(db, oldVersion, newVersion);
    }
*/
/*
    private static void insertUser(SQLiteDatabase db, String username, String email, String firstname, String lastname, String country) {
        ContentValues userValues = new ContentValues();
        userValues.put("USERNAME", username);
        userValues.put("EMAIL", email);
        userValues.put("FIRSTNAME", firstname);
        userValues.put("LASTNAME", lastname);
        userValues.put("COUNTRY", country);
        db.insert("USER", null, userValues);
    }
*/
/*
    private static void insertAccount(SQLiteDatabase db, String username, String password) {
        ContentValues accountValues = new ContentValues();
        accountValues.put("USERNAME", username);
        accountValues.put("PASSWORD", password);
    }
*/
    // Add accounts to the Account Table
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get writable database
        ContentValues values = new ContentValues(); // Create content values to insert
        values.put(COLUMN_USERNAME, account.userName); // Put username in @values
        values.put(COLUMN_EMAIL, account.email); // Put email in @values
        values.put(COLUMN_PASSWORD, account.password); // Put password in @values
        long todo_id = db.insert(ACCOUNT_TABLE_NAME, null, values); // Insert row
    }

    public Account Authenticate(Account account) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE_NAME, // Select table
                new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_PASSWORD}, // Select columns I want to query
                COLUMN_EMAIL + "=?",
                new String[]{account.email}, // Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            // If cursor has value, then there is an account associated with this username in db
            Account account1 = new Account(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            if (account.userName.equalsIgnoreCase(account1.userName)) {
                return account1;
            }
        }
        // Username does not match, or there is no record with that username
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE_NAME, // Select table
                new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_PASSWORD},
                COLUMN_EMAIL + "=?",
                new String[]{email}, // Where clause
                null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            // If cursor has value, then there is an account associated with this username
            return true;
        }
        // If username does not exist
        return false;
    }
/*
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
              insertUser(db, "CL1FF72", "cliffsemail@hotmail.com", "Cliff", "Cliffton", "CANADA");
              insertUser(db, "RSODER", "rachsoderberg@gmail.com", "Rachel", "Soderberg", "USA");

              // Create Account info table
              db.execSQL("CREATE TABLE ACCOUNT ("
              + "_id PRIMARY KEY AUTOINCREMENT, "
              + "USERNAME TEXT, "
              + "PASSWORD TEXT);");
              // Populate Account table data (for testing)
              insertAccount(db, "CL1FF72", "123abc");
              insertAccount(db, "RSODER", "abc123");
          }
     }
*/
}
