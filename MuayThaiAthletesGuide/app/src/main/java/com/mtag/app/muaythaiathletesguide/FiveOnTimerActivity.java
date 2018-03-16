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

public class FiveOnTimerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private int seconds = 0; // Number of seconds passed
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    private int timeCap = 0; // Custom max time, stop timer when reached and reset here for countdown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_on_timer);

        // Timer Selection Spinner
        Spinner timerSpinner = (Spinner) findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(adapter);
        timerSpinner.setSelection(adapter.getPosition("Pro: \"5 On 1 Off\""));
        // Spinner click listener
        timerSpinner.setOnItemSelectedListener(this);

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null && running) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        String selection = parent.getItemAtPosition(pos).toString();
        // Call Timer types when corresponding position is chosen
        switch(pos) {
            case 0: // Basic Stopwatch: Count from 0:00:00 to 99:59:59 (or cap)
                onDestroy();
                running = false;
                // Switch to Basic Timer Activity
                Intent intent = new Intent(this, BasicTimerActivity.class);
                startActivity(intent);
                break;
            case 1: // Countdown: Count from 99:59:59 (or cap) to 0:00:00
                onDestroy();
                running = false;
                // Switch to Countdown Timer Activity
                intent = new Intent(this, CountdownTimerActivity.class);
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
                // Switch to Fight Gone Bad Activity
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
                seconds = 0;
                runFiveOnTimer();
                break;
            default:
                running = false;
                seconds = 0;
                Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_LONG).show();
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
        seconds = timeCap; // Reset seconds to zero
    }

    private void runFiveOnTimer() {
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
                // Beep every 5 and 6 minutes
                if (seconds > 0 && seconds % 300 == 0) {
                    Toast.makeText(getApplicationContext(), "Beep!", Toast.LENGTH_SHORT).show();
                }
                if (seconds > 0 && seconds % 360 == 0) {
                    Toast.makeText(getApplicationContext(), "Beep Beep!", Toast.LENGTH_SHORT).show();
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