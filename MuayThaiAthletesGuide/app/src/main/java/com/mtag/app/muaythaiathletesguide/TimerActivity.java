package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

// Adding sounds tutorial: http://www.c-sharpcorner.com/UploadFile/1e5156/add-sound-to-your-application-in-android-studio/
// Timer sound playback library: https://github.com/delight-im/Android-Audio

// Gradient Buttons: https://github.com/sapandiwakar/PSGradientButtons

// Basic Stopwatch: Count from 0:00 to 99:59 (or cap)
// Countdown: Count from 99:59 (or cap) to 0:00
// Tabata: Beep every 20th and 30th second
//         Reset to 0:00 on each 30th second
// Fight Gone Bad: 17min cap, beep on each minute
// "3 On 1 Off": Beep every 3rd and 4th minute

public class TimerActivity extends Activity {
    private int seconds = 0; // Number of seconds passed
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Timer Selection Spinner
        Spinner spinner = (Spinner) findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        // Specify which layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runBasicTimer(); // Start stopwatch when method is created
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
        running = false; // Stop stopwatch
        seconds = 0; // Reset seconds to zero
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
                // Post code again with delay of one second
                handler.postDelayed(this, 1000);
            }
        });
    }
}