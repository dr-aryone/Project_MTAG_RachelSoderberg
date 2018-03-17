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

// https://stackoverflow.com/questions/18614250/how-to-display-database-content-on-android-listview
// Tutorial on Listview & SQLite: http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html

// Great tutorial: http://hmkcode.com/android-simple-sqlite-database-tutorial/
//         GitHub: https://github.com/hmkcode/Android/tree/master/android-sqlite

public class CombosActivity extends Activity {
    private ComboSqliteHelper db;
    private SimpleCursorAdapter comboAdapter;
    /* TODO: Remove, unused
    EditText editTextComboName;
    EditText editTextCombo;
    TextInputLayout textInputLayoutComboName;
    TextInputLayout textInputLayoutCombo;
    Button buttonStoreCombo;
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combos);

        db = new ComboSqliteHelper(this);

        // Add combos to database
        db.addCombo(new Combo("Combo #1", "R Kick - Cross - R Kick, L Kick - Cross - R Kick,\n " +
                "Jab - Cross - Hook - Cross, L Elbow - R Elbow - R Knee - L Knee"));
        db.addCombo(new Combo("Combo #3", "Hook - R Knee - R Kick, Cross - Hook - R Kick"));
        db.addCombo(new Combo("Combo #5 (Bunker Fairtex)", "L Teep - L Knee - L Kick, R Teep - R Knee - R Kick"));
        db.addCombo(new Combo("Combo #7", "Jab - L Teep - L Kick , 2x L Kick, L Kick, Block (R Kick), L Kick"));
        db.addCombo(new Combo("Combo #8", "Jab - R Teep - R Kick, 2x R Kick, R Kick, Block (L Kick), R Kick"));
        db.addCombo(new Combo("Combo #10", "2x R Kick, Parry & Step Out (Jab) - 2x L Kick, Parry & Step Out (Cross) - 2x R Kick"));
        db.addCombo(new Combo("\"Bent Arm\" Combo #10", "Jab - Cross - Hook - Jab - Cross - Body Hook - Cross - Hook - Cross"));
        db.addCombo(new Combo("\"Rainbow\"", "L Kick - Cross - L Hook - R Kick, R Kick - L Hook - Cross - L Kick"));
        db.addCombo(new Combo("\"Pivots\"", "Jab - Cross - L Knee - R Elbow - Pivot* - R Kick     *L Hand on R Pad\n" +
                "Jab - Cross - R Knee - L Elbow - Pivot* - L Kick      *R Hand on L Pad"));
        db.addCombo(new Combo("Ten Count", "Jab\n" + "2x Jab\n" + "3x Jab\n" + "Cross\n" + "2x Cross\n" +
                "Jab - Cross\n" + "Cross - Jab\n" + "2x Jab - Cross\n" + "Cross - Jab - Cross\n" + "Jab - Cross - Jab"));
        db.addCombo(new Combo("Simple Builder", "(0:00) 2x Jab\n" + "(1:00) 2x Cross\n" + "(2:00) L Hook - Cross"));

        // 3/2/18 Tutorial https://www.javacodegeeks.com/2013/10/android-json-tutorial-create-and-parse-json-data.html ////////////////////
        // TODO: Reading from string array for presentation, need to hook up to database later
        // Need to use a for loop (using i < db.count()) to fill array. May need a count function..
        final ListView listViewCombos = (ListView) findViewById(R.id.listViewCombos);
        String[] values = new String[]{ "Combo #1:\nR Kick - Cross - R Kick, L Kick - Cross - R Kick,\n" + "Jab - Cross - Hook - Cross, L Elbow - R Elbow - R Knee - L Knee",
                "Combo #3\nHook - R Knee - R Kick, Cross - Hook - R Kick",
                "Combo #5 (Bunker Fairtex\nL Teep - L Knee - L Kick, R Teep - R Knee - R Kick",
                "Combo #7\nJab - L Teep - L Kick , 2x L Kick, L Kick, Block (R Kick), L Kick",
                "Combo #8\nJab - R Teep - R Kick, 2x R Kick, R Kick, Block (L Kick), R Kick",
                "Combo #10\n2x R Kick, Parry & Step Out (Jab) - 2x L Kick, Parry & Step Out (Cross) - 2x R Kick",
                "\"Bent Arm\" Combo #10\nJab - Cross - Hook - Jab - Cross - Body Hook - Cross - Hook - Cross",
                "\"Rainbow\"\nL Kick - Cross - L Hook - R Kick, R Kick - L Hook - Cross - L Kick",
                "\"Pivots\"\nJab - Cross - L Knee - R Elbow - Pivot* - R Kick     *L Hand on R Pad\n"
                        + "Jab - Cross - R Knee - L Elbow - Pivot* - L Kick      *R Hand on L Pad",
                "Ten Count\nJab\n" + "2x Jab\n" + "3x Jab\n" + "Cross\n" + "2x Cross\n" + "Jab - Cross\n" +
                        "Cross - Jab\n" + "2x Jab - Cross\n" + "Cross - Jab - Cross\n" + "Jab - Cross - Jab\n",
                "Simple Builder\n(0:00) 2x Jab\n" + "(1:00) 2x Cross\n" + "(2:00) L Hook - Cross"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++)
            list.add(values[i]);

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewCombos.setAdapter(adapter);

/////////////////////////////////
        // TODO: Reading to ListView from Database
        /*final ListView listViewCombos = (ListView) findViewById(R.id.listViewCombos);
        final ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < db.combosCount; i++) {
            list.add(ComboSqliteHelper.KEY_COMBO_NAME + "\n" + ComboSqliteHelper.KEY_COMBO); // TODO: Current output = comboName combo. Need a way to get specific rows
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listViewCombos.setAdapter(adapter);*/

/////////////////////////////////
        /* Remove item on click
        listViewCombos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });*/
    }
    ///////////////////////////////
    // TODO: May need this when getting all combos for ListView
    // Get all combos
    //List<Combo> list = db.getAllCombos();
    //
    // Delete one combo
    // db.deleteCombo(list.get(0);
    ///////////////////////////////

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
        public boolean hasStableIds() {
            return true;
        }
    }
}