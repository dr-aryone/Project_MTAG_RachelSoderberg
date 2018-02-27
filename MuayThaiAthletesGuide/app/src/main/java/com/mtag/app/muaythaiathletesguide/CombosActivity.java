package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SimpleCursorAdapter;

import java.util.List;

// https://stackoverflow.com/questions/18614250/how-to-display-database-content-on-android-listview
// Tutorial on Listview & SQLite: http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html

// Great tutorial: http://hmkcode.com/android-simple-sqlite-database-tutorial/
//         GitHub: https://github.com/hmkcode/Android/tree/master/android-sqlite

public class CombosActivity extends Activity {
    private ComboSqliteHelper db;
    private SimpleCursorAdapter dataAdapter;
    /*EditText editTextComboName;
    EditText editTextCombo;
    TextInputLayout textInputLayoutComboName;
    TextInputLayout textInputLayoutCombo;
    Button buttonStoreCombo;
    */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combos);

        db = new ComboSqliteHelper(this);

        db.addCombo(new Combo("Combo #1", "R Kick - Cross - R Kick, L Kick - Cross - R Kick, " +
                "Jab - Cross - Hook - Cross, L Elbow - R Elbow - R Knee - L Knee"));
        db.addCombo(new Combo("Combo #3", "Hook - R Knee - R Kick, Cross - Hook - R Kick"));
        db.addCombo(new Combo("Combo #5 (Bunker Fairtex)", "L Teep - L Knee - L Kick, R Teep - R Knee - R Kick"));

        // Get all combos
        List<Combo> list = db.getAllCombos();

        // Delete one combo
        // db.deleteCombo(list.get(0);

/////////////////////////////////////////////////
        //initViews();

        // Store button click event
        /*buttonStoreCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Validate user input
                // if  (validate()) ...
                String ComboName = editTextComboName.getText().toString();
                String Combo = editTextCombo.getText().toString();

                comboSqliteHelper.addCombo(new Combo(null, ComboName, Combo));
                Snackbar.make(buttonStoreCombo, "Combo created successfully!", Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, Snackbar.LENGTH_LONG);
            }
        });*/
    }

/*
    public void onCombosActivity(View view) {
        Intent intent = new Intent(this, CombosActivity.class);
        startActivity(intent);

        // TODO: Remove toast after testing
        Context context = getApplicationContext();
        CharSequence text = "Page refreshed!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        ///////////////////////////////////
    }

    // Connect XML views to their objects
    private void initViews() {
        editTextComboName = (EditText) findViewById(R.id.editTextEmail);
        editTextCombo = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutComboName = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutCombo = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonStoreCombo = (Button) findViewById(R.id.buttonStoreCombo);
    }*/
}