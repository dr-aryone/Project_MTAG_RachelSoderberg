package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

// Adding sounds tutorial: http://www.c-sharpcorner.com/UploadFile/1e5156/add-sound-to-your-application-in-android-studio/
// Timer sound playback library: https://github.com/delight-im/Android-Audio

// Gradient Buttons: https://github.com/sapandiwakar/PSGradientButtons

public class BasicTimerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private int seconds = 0; // Number of seconds passed
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    private int timeCap = 6000; // Custom max time, stop timer when reached and reset here for countdown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_timer);

        // Timer Selection Spinner
        Spinner timerSpinner = (Spinner) findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(adapter);
        timerSpinner.setSelection(adapter.getPosition("Basic Stopwatch"));
        // Spinner click listener
        timerSpinner.setOnItemSelectedListener(this);

        // Time Cap Selection Spinner
        Spinner timecapSpinner = (Spinner) findViewById(R.id.timecap_spinner);
        ArrayAdapter<CharSequence> capadapter = ArrayAdapter.createFromResource(this,
                R.array.timecap_spinner, android.R.layout.simple_spinner_item);
        capadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timecapSpinner.setAdapter(capadapter);
        //timecapSpinner.setSelection(capadapter.getPosition("100:00"));
        timecapSpinner.setOnItemSelectedListener(this);

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null && running) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        switch(parent.getId()) {
            case R.id.timer_spinner:
                String selection = parent.getItemAtPosition(pos).toString(); // TODO: Variable to use spinner position, if needed
                // Call Timer types when corresponding position is chosen
                switch (pos) {
                    case 0: // Basic Stopwatch: Count from 0:00:00 to 99:59:59 (or cap)
                        onDestroy();
                        running = false; // Stop clock
                        runBasicTimer();
                        break;
                    case 1: // Countdown: Count from 99:59:59 (or cap) to 0:00:00
                        onDestroy();
                        running = false;
                        // Switch to Countdown Timer Activity
                        Intent intent = new Intent(this, CountdownTimerActivity.class);
                        startActivity(intent);
                        break;
                    case 2: // Tabata: Beep every 20th and 30th second. Reset to 0:00:00 on each 30th second
                        onDestroy();
                        running = false;
                        // Switch to Tabata Timer Activity
                        intent = new Intent(this, TabataTimerActivity.class);
                        startActivity(intent);
                        break;
                    case 3: // Fight Gone Bad: 17min cap, beep on each minute
                        onDestroy();
                        running = false;
                        // Switch to Fight Gone Bad Timer Activity
                        intent = new Intent(this, FGBTimerActivity.class);
                        startActivity(intent);
                        break;
                    case 4: // "3 On 1 Off": Beep every 3rd and 4th minute
                        onDestroy();
                        running = false;
                        // Switch to Three On One Off Timer Activity
                        intent = new Intent(this, ThreeOnTimerActivity.class);
                        startActivity(intent);
                        break;
                    case 5: // "5 On 1 Off": Beep every 5th and 6th minute
                        onDestroy();
                        running = false;
                        // Switch to Five On One Off Timer Activity
                        intent = new Intent(this, FiveOnTimerActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        running = false;
                        seconds = 0;
                        Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case R.id.timecap_spinner :
                // Set time cap to user's selection
                switch(pos) {
                    case 1 : // 100:00
                        timeCap = 6000;
                        break;
                    case 2 : // 60:00
                        timeCap = 3600;
                        break;
                    case 3 : // 50:00
                        timeCap = 3000;
                        break;
                    case 4 : // 45:00
                        timeCap = 2700;
                        break;
                    case 5 : // 40:00
                        timeCap = 2400;
                        break;
                    case 6 : // 35:00
                        timeCap = 2100;
                        break;
                    case 7 : // 30:00
                        timeCap = 1800;
                        break;
                    case 8 : // 29:00
                        timeCap = 1740;
                        break;
                    case 9 : // 28:00
                        timeCap = 1680;
                        break;
                    case 10 : // 27:00
                        timeCap = 1620;
                        break;
                    case 11 : // 26:00
                        timeCap = 1560;
                        break;
                    case 12 : // 25:00
                        timeCap = 1500;
                        break;
                    case 13 : // 24:00
                        timeCap = 1440;
                        break;
                    case 14 : // 23:00
                        timeCap = 1380;
                        break;
                    case 15 : // 22:00
                        timeCap = 1320;
                        break;
                    case 16 : // 21:00
                        timeCap = 1260;
                        break;
                    case 17 : // 20:00
                        timeCap = 1200;
                        break;
                    case 18 : // 19:00
                        timeCap = 1140;
                        break;
                    case 19 : // 18:00
                        timeCap = 1080;
                        break;
                    case 20 : // 17:00
                        timeCap = 1020;
                        break;
                    case 21 : // 16:00
                        timeCap = 960;
                        break;
                    case 22 : // 15:00
                        timeCap = 900;
                        break;
                    case 23 : // 14:00
                        timeCap = 840;
                        break;
                    case 24 : // 13:00
                        timeCap = 780;
                        break;
                    case 25 : // 12:00
                        timeCap = 720;
                        break;
                    case 26 : // 11:00
                        timeCap = 660;
                        break;
                    case 27 : // 10:00
                        timeCap = 600;
                        break;
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent){
        // Another interface callback
    }

    @Override
    // Save the state of variables
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // If the stopwatch was running at stop, set it running again
        if (wasRunning)
            running = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Record state of stopwatch, running or not running
        wasRunning = running;
        running = false;
    }

    public void onClickStart(View view) {
        running = true; // Start stopwatch
    }

    public void onClickStop(View view) {
        running = false; // Stop stopwatch
    }

    public void onClickReset(View view) {
        onPause();
        seconds = 0;
    }

    private void runBasicTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                // Format time to hours, minutes, and seconds
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                // TODO: Test output, delete
                Toast.makeText(getApplicationContext(), "Time Cap: " + timeCap, Toast.LENGTH_SHORT).show();
                // Stop timer when time cap is reached
                if (seconds >= timeCap) {
                    Toast.makeText(getApplicationContext(), "Cap Beep!", Toast.LENGTH_SHORT).show();
                    onPause();
                }
                // Don't allow timer to go over 99:59:59
                if (seconds >= 359999) {
                    running = false;
                    Toast.makeText(getApplicationContext(), "Maximum time reached", Toast.LENGTH_LONG).show();
                }
                // Post code again with delay of one second
                handler.postDelayed(this, 1000);
            }
        });
    }
}