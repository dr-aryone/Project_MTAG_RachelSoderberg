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

        db.addMartial(new Martial("Boxing Workout 1", "(0:00) Jab - Cross - Hook\n(1:00) Rapid Fire Jab - Cross\n(2:00) Heavy L Hook - Heavy R Hook", "3:00 on Clock"));
        db.addMartial(new Martial("Boxing Workout 2", "(0:00) Jab - Parry - Right - Duck\n(1:00)Rapid Fire L Hook - R Hook\n(2:00)Jab - Heavy Cross", "3:00 on Clock"));
        db.addMartial(new Martial("Boxing Workout 3", "(0:00) Jab - L Hook Body - Cross\n(1:00) Jab - Jab - Heavy Cross\n(2:00) Rapid Fire Jab - Cross", "3:00 on Clock"));
        db.addMartial(new Martial("Muay Thai Workout 1", "(0:00) Rapid Fire L Hook - R Hook\n(1:00) Alternate Teeps\n(2:00) Rapid Fire Jab - Cross", "3:00 on Clock"));
        db.addMartial(new Martial("Muay Thai Workout 2", "(0:00) Jab - Cross - Block (L Kick) - L Kick\n(1:00) 2x R Kick - 2x L Kick\n(2:00) Rapid Fire Alternate Teeps", "3:00 on Clock"));
        db.addMartial(new Martial("Muay Thai Workout 3", "(0:00) R Teep - R Knee - R Elbow\n(1:00) L Teep - L Knee - L Elbow\n(2:00) Rapid Fire Jab - Cross", "3:00 on Clock"));

        // TODO: Presentation string array output to ListView
        final ListView listViewMartial = (ListView) findViewById(R.id.listViewMartial);
        String[] values = new String[]{ "Boxing Workout 1\n(0:00) Jab - Cross - Hook\n(1:00) Rapid Fire Jab - Cross\n(2:00) Heavy L Hook - Heavy R Hook\n3:00 on Clock",
                "Boxing Workout 2\n(0:00) Jab - Parry - Right - Duck\n(1:00)Rapid Fire L Hook - R Hook\n(2:00)Jab - Heavy Cross\n3:00 on Clock",
                "Boxing Workout 3\n(0:00) Jab - L Hook Body - Cross\n(1:00) Jab - Jab - Heavy Cross\n(2:00) Rapid Fire Jab - Cross\n3:00 on Clock",
                "Muay Thai Workout 1\n(0:00) Rapid Fire L Hook - R Hook\n(1:00) Alternate Teeps\n(2:00) Rapid Fire Jab - Cross\n3:00 on Clock",
                "Muay Thai Workout 2\n(0:00) Jab - Cross - Block (L Kick) - L Kick\n(1:00) 2x R Kick - 2x L Kick\n(2:00) Rapid Fire Alternate Teeps\n3:00 on Clock",
                "Muay Thai Workout 3\n(0:00) R Teep - R Knee - R Elbow\n(1:00) L Teep - L Knee - L Elbow\n(2:00) Rapid Fire Jab - Cross\n3:00 on Clock"};

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
