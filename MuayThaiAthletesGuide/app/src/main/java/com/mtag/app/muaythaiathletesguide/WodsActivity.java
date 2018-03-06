package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WodsActivity extends Activity {
    private WodSqliteHelper db;
    private SimpleCursorAdapter wodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wods);

        db = new WodSqliteHelper(this);

        db.addWod(new Wod("Fran", "21-15-9\nThrusters\nPull Ups", "For Time"));
        db.addWod(new Wod("Annie", "50-40-30-20-10\nSit Ups\nDouble Unders", "For Time"));

        // TODO: Presentation string array output to ListView
        final ListView listViewWods = (ListView) findViewById(R.id.listViewWods);
        String[] values = new String[]{ "Fran:\n21-15-9\nThrusters\nPull Ups\nFor Time",
                "Annie:\n50-40-30-20-10\nSit Ups\nDouble Unders\nFor Time"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++)
            list.add(values[i]);

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewWods.setAdapter(adapter);
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
