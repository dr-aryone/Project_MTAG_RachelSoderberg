package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserPortalActivity extends Activity {
    private PortalSqliteHelper db;

    private EditText nameEditText;
    private EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);

        db = new PortalSqliteHelper(this);

        //find editText
        nameEditText = (EditText) findViewById(R.id.editTextInName);
        descEditText = (EditText) findViewById(R.id.editTextInDesc);
    }

    public void onUserPortalActivity(View view) {
        String nameString = nameEditText.getText().toString();
        String descString = descEditText.getText().toString();

        Intent intent = new Intent(this, UserPortalActivity.class);

        intent.putExtra("name", nameString);
        intent.putExtra("desc", descString);

        db.addPortal(new Portal());
        startActivity(intent);

        /////////////////////////////////
        // TODO: Reading to ListView from Database
        /*
        final ListView listViewPortal = (ListView) findViewById(R.id.listViewPortal);
        final ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < db.combosCount; i++) {
            list.add(PortalSqliteHelper.KEY_IN_NAME + "\n" + ComboSqliteHelper.KEY_IN_DESC);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewPortal.setAdapter(adapter);
        */
        ///////////////////////////////
    }
}
