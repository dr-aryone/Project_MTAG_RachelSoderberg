package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiftingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifting);

        final ListView listViewLifts = findViewById(R.id.listViewLifts);
        String[] values = new String[]{ "Deadlift\n5 sets of 5 reps, increase weight each set",
                "Front Squat\n7 sets of 3 reps, increase weight each set",
                "Shoulder Press\n3 sets of 5 reps, then 3 sets of 3 reps heavier",
                "Bench Press\n4 sets of 12 reps, work up to one heavy weight",
                "Back Squat\n5 sets of 6 reps, increase weight each set",
                "Overhead Squat\n5 sets of 3 reps, work up to one heavy weight",
                "Power Clean\n4 sets of 4 reps, increase weight each set",
                "Dumbbell Bench Press\n4 sets of 12 reps, work up to one heavy weight",
                "Dumbbell Shrug\n4 sets of 12 reps, increase weight each set",
                "Dumbbell or Barbell Row\n3 sets of 12 reps, work up to one heavy weight",
                "Kettlebell Clean and Jerk\n3 sets of 10 reps, work up to one heavy weight"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++)
            list.add(values[i]);

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewLifts.setAdapter(adapter);
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
