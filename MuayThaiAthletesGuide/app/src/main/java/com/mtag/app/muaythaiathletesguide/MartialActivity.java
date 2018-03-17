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

public class MartialActivity extends Activity {
    private MartialSqliteHelper db;
    private SimpleCursorAdapter martialAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martial);

        db = new MartialSqliteHelper(this);

        db.addMartial(new Martial("Martial Workout Name", "Martial Workout Description", "Time Limit/Suggestion"));
        db.addMartial(new Martial("Martial Workout Name 2", "Martial Workout Description", "Time Limit/Suggestion"));

        // TODO: Presentation string array output to ListView
        final ListView listViewMartial = (ListView) findViewById(R.id.listViewMartial);
        String[] values = new String[]{ "Martial Name\nMartial Workout Description\nTime Limit or Suggestion",
                "Martial Name\nMartial Workout Description\nTime Limit or Suggestion"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++)
            list.add(values[i]);

        final MartialActivity.StableArrayAdapter adapter = new MartialActivity.StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewMartial.setAdapter(adapter);
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
