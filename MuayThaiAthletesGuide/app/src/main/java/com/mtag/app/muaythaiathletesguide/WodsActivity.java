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

        db.addWod(new Wod("Annie", "50-40-30-20-10\nSit Ups\nDouble Unders", "For Time"));
        db.addWod(new Wod("Barbara", "20 Pull Ups\n30 Push Ups\n40 Sit Ups\n50 Air Squats\n3min Rest", "For Time"));
        db.addWod(new Wod("Blackjack", "20 Push Ups, 1 Sit Up\n19 Push Ups, 2 Sit Ups\n18 Push Ups, 3 Sit Ups\n...\n2 Push Ups, 19 Sit Ups\n1 Push Up, 20 Sit Ups", "For Time"));
        db.addWod(new Wod("Bradley", "10 Rounds:\n100m Sprint\n10 Pull Ups\n100m Sprint\n10 Burpees\n30sec Rest", "For Time"));
        db.addWod(new Wod("Chelsea", "5 Pull Ups\n10 Push Ups\n15 Air Squats", "30min EMOM"));
        db.addWod(new Wod("Cindy", "5 Pull Ups\n10 Push Ups\n15 Air Squats", "20min AMRAP"));
        db.addWod(new Wod("Death By Burpees", "Each minute, increase the number of burpees:\n1:00 - 1 Burpee\n2:00 - 2 Burpees\n3 - 3 Burpees\n... Until the round is incomplete", "For Time/Reps"));
        db.addWod(new Wod("Murph", "1mi Run\n100 Pull Ups\n200 Push Ups\n300 Air Squats\n1mi Run", "For Time"));
        db.addWod(new Wod("Tabata Something Else", "Score is total reps:\n8 Intervals Pull Ups\n8 Intervals Push Ups\n8 Intervals Sit Ups\n8 Intervals Air Squats", "Tabata: 32x 20/10sec intervals"));
        db.addWod(new Wod("The Longest Mile", "4 Rounds:\n10 Burpees\n100m Run\n10 Air Squats\n100m Run\n10 Push Ups\n100m Run\n10 Sit Ups\n100m Run", "For Time"));
        db.addWod(new Wod("12 Down to 1", "Push Ups\nPull Ups", "For Time"));

        // TODO: Presentation string array output to ListView
        final ListView listViewWods = (ListView) findViewById(R.id.listViewWods);
        String[] values = new String[]{ "Annie\n50-40-30-20-10\nSit Ups\nDouble Unders\nFor Time",
                "Barbara\n5 Rounds for Time:\n20 Pull Ups\n30 Push Ups\n40 Sit Ups\n50 Air Squats\n3min Rest",
                "Blackjack\n20 Push Ups, 1 Sit Up\n19 Push Ups, 2 Sit Ups\n18 Push Ups, 3 Sit Ups\n...\n2 Push Ups, 19 Sit Ups\n1 Push Up, 20 Sit Ups",
                "Bradley\n10 Rounds for Time:\n100m Sprint\n10 Pull Ups\n100m Sprint\n10 Burpees\n30sec Rest",
                "Chelsea\n30min EMOM:\n5 Pull Ups\n10 Push Ups\n15 Air Squats",
                "Cindy\n20min AMRAP:\n5 Pull Ups\n10 Push Ups\n15 Air Squats",
                "Death By Burpees\nEach minute, increase the number of burpees:\n1:00 - 1 Burpee\n2:00 - 2 Burpees\n3 - 3 Burpees\n... Until the round is incomplete",
                "Murph\n1mi Run\n100 Pull Ups\n200 Push Ups\n300 Air Squats\n1mi Run",
                "Tabata Something Else\n16min Clock (32x 20/10sec intervals)\nScore is total reps:\n8 Intervals Pull Ups\n8 Intervals Push Ups\n8 Intervals Sit Ups\n8 Intervals Air Squats",
                "The Longest Mile\n4 Rounds for Time:\n10 Burpees\n100m Run\n10 Air Squats\n100m Run\n10 Push Ups\n100m Run\n10 Sit Ups\n100m Run",
                "12 Down to 1\nPush Ups\nPull Ups"};

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
