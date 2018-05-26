package com.mtag.app.muaythaiathletesguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PortalDetail extends AppCompatActivity implements android.view.View.OnClickListener{
    Button saveButton, deleteButton;
    Button backButton;
    EditText editTextName;
    EditText editTextDesc;
    private int _Portal_Id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_detail);

        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        backButton = (Button) findViewById(R.id.backButton);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDesc = (EditText) findViewById(R.id.editTextDesc);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        _Portal_Id =0;
        Intent intent = getIntent();
        _Portal_Id =intent.getIntExtra("portal_id", 0);
        PortalRepo repo = new PortalRepo(this);
        Portal portal = new Portal();
        portal = repo.getPortalById(_Portal_Id);

        editTextName.setText(portal.inName);
        editTextDesc.setText(portal.inDesc);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.saveButton)){
            PortalRepo repo = new PortalRepo(this);
            Portal portal = new Portal();
            portal.inDesc = editTextDesc.getText().toString();
            portal.inName = editTextName.getText().toString();
            portal.id = _Portal_Id;

            if (_Portal_Id == 0){
                _Portal_Id = repo.insert(portal);
                Toast.makeText(this,"New Item Inserted", Toast.LENGTH_SHORT).show();
            }
            else{
                repo.update(portal);
                Toast.makeText(this,"Record updated",Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == findViewById(R.id.deleteButton)){
            PortalRepo repo = new PortalRepo(this);
            repo.delete(_Portal_Id);
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view == findViewById(R.id.backButton)){
            finish();
        }
    }
}
