package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserPortalActivity extends Activity {
    PortalSqliteHelper portalSqliteHelper;
    EditText editTextName;
    EditText editTextDesc;
    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutDesc;
    Button buttonPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);

        portalSqliteHelper = new PortalSqliteHelper(this);
        initTextViewPortal();
        initViews();

        buttonPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (validate()) {
                    String Name = editTextName.getText().toString();
                    String Desc = editTextDesc.getText().toString();

                    if (!portalSqliteHelper.isNameExists(Name)) {
                        portalSqliteHelper.addPortal(new Portal(Name, Desc));
                        Snackbar.make(buttonPortal, "Added successfully! ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onUserPortalActivity(view);
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {
                        Snackbar.make(buttonPortal, "That name already exists! ", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void onUserPortalActivity(View view) {
        Intent intent = new Intent(this, UserPortalActivity.class);
        startActivity(intent);
    }

    private void initTextViewPortal() {
        TextView textViewPortal = findViewById(R.id.textViewPortal);
        textViewPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextDesc = findViewById(R.id.editTextDesc);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutDesc = findViewById(R.id.textInputLayoutDesc);

        buttonPortal = findViewById(R.id.buttonPortal);
    }

    public boolean validate() {
        boolean valid = false;
        String Name = editTextName.getText().toString();
        String Desc = editTextDesc.getText().toString();

        if (Name.isEmpty()) {
            valid = false;
            textInputLayoutName.setError("Please enter valid username!");
        }
        else {
            valid = true;
            textInputLayoutName.setError(null);
        }

        if (Desc.isEmpty()) {
            valid = false;
            textInputLayoutDesc.setError("Please enter valid description!");
        }
        else {
            valid = true;
            textInputLayoutDesc.setError(null);
        }
        return valid;
    }
}
