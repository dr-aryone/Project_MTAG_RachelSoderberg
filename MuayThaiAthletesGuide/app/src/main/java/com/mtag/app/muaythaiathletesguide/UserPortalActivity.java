package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;

public class UserPortalActivity extends Activity {
    private PortalSqliteHelper db;

    private EditText nameEditText;
    private EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);

        //db = new PortalSqliteHelper(this);

        /////////////////////////////////
        // TODO: Reading to ListView from Database
        //final ListView listViewPortal = (ListView) findViewById(R.id.listViewPortal);
        //final ArrayList<String> list = new ArrayList<String>();

        //for (int i = 0; i < db.portalCount; i++) {
        //    list.add(PortalSqliteHelper.KEY_IN_NAME + "\n" + PortalSqliteHelper.KEY_IN_DESC);
        //}

        //for (int i = 0; i < values.length; i++)
            //list.add(values[i]);

        //final StableArrayAdapter adapter = new StableArrayAdapter(this,
        //        android.R.layout.simple_list_item_1, list);
        //listViewPortal.setAdapter(adapter);
        ///////////////////////////////

        //find editText
        //nameEditText = (EditText) findViewById(R.id.editTextInName);
        //descEditText = (EditText) findViewById(R.id.editTextInDesc);
    }

    public void onUserPortalActivity(View view) {
        //find editText
        nameEditText = (EditText) findViewById(R.id.editTextInName);
        descEditText = (EditText) findViewById(R.id.editTextInDesc);

        String nameString = nameEditText.getText().toString();
        String descString = descEditText.getText().toString();

        Intent intent = new Intent(this, UserPortalActivity.class);

        intent.putExtra("name", nameString);
        intent.putExtra("desc", descString);

        //db.addPortal(new Portal(nameString, descString));
        startActivity(intent);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); i++)
                mIdMap.put(objects.get(i), i);
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);

            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() { return true; }
    }
}
