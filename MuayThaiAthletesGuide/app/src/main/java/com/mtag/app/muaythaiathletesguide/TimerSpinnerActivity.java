package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class TimerSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_spinner);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        // An item was selected. You can retrieve the selected item using parent.getItemAtPosition(pos)
        parent.getItemAtPosition(pos);

        // Call Timer Activities when each corresponding pos is called
    }

    public void onNothingSelected(AdapterView<?> parent){
        // Another interface callback
    }
}
