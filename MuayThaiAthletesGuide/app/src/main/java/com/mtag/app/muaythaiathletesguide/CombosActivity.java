package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CombosActivity extends Activity {
    EditText editTextComboName;
    EditText editTextCombo;
    TextInputLayout textInputLayoutComboName;
    TextInputLayout textInputLayoutCombo;
    Button buttonStoreCombo;
    ComboSqliteHelper comboSqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combos);
        comboSqliteHelper = new ComboSqliteHelper(this);
        initViews();

        // Login button click event
        buttonStoreCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ComboName = editTextComboName.getText().toString();
                String Combo = editTextCombo.getText().toString();

                // TODO: Validate user input

                comboSqliteHelper.addCombo(new Combo(null, ComboName, Combo));
                Snackbar.make(buttonStoreCombo, "Combo created successfully!", Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, Snackbar.LENGTH_LONG);
            }
        });
    }

    // Connect XML views to their objects
    private void initViews() {
        editTextComboName = (EditText) findViewById(R.id.editTextEmail);
        editTextCombo = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutComboName = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutCombo = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonStoreCombo = (Button) findViewById(R.id.buttonStoreCombo);
    }
}