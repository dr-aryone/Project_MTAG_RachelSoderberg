package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
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

// Stackoverflow Question: https://stackoverflow.com/questions/48875368/android-studio-stopping-killing-timer-with-switch-statement/48891908?noredirect=1#comment84834219_48891908

public class TimerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Runnable activeTimer; // Hold reference to runnable

    private int seconds = 0; // Number of seconds passed
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    private int timeCap = 0; // Custom max time, stop timer when reached and reset here for countdown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Timer Selection Spinner
        Spinner spinner = (Spinner) findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null && running) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        String selection = parent.getItemAtPosition(pos).toString();

        // TODO: Remove Toast outputs after testing
        // Call Timer types when corresponding position is chosen
        switch(pos) {
            case 0: // Basic Stopwatch: Count from 0:00:00 to 99:59:59 (or cap)
                onDestroy();
                running = false; // Stop clock
                seconds = 0; // Reset seconds to zero
                timeCap = seconds; // Set time cap to match seconds on the clock, for reset point
                runBasicTimer();
                break;
            case 1: // Countdown: Count from 99:59:59 (or cap) to 0:00:00
                Toast.makeText(parent.getContext(), "Selected: " + selection, Toast.LENGTH_LONG).show();
                onDestroy();
                running = false;
                seconds = 1200; // Default cap 20:00:00
                timeCap = seconds;
                //runCountdownTimer();
                break;
            case 2: // Tabata: Beep every 20th and 30th second. Reset to 0:00:00 on each 30th second
                Toast.makeText(parent.getContext(), "Selected: " + selection, Toast.LENGTH_LONG).show();
                running = false;
                seconds = 0;
                //runTabataTimer();
                break;
            case 3: // Fight Gone Bad: 17min cap, beep on each minute
                Toast.makeText(parent.getContext(), "Selected: " + selection, Toast.LENGTH_LONG).show();
                running = false;
                seconds = 0;
                runFGBTimer();
                break;
            case 4: // "3 On 1 Off": Beep every 3rd and 4th minute
                Toast.makeText(parent.getContext(), "Selected: " + selection, Toast.LENGTH_LONG).show();
                running = false;
                seconds = 0;
                //runThreeOneTimer();
                break;
            case 5: // "5 On 1 Off": Beep every 5th and 6th minute
                Toast.makeText(parent.getContext(), "Selected: " + selection, Toast.LENGTH_LONG).show();
                running = false;
                seconds = 0;
                //runFiveOneTimer();
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
        seconds = timeCap; // Reset seconds to zero
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

    private void runCountdownTimer() {
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
                    seconds--;
                }
                // Don't allow timer to go under 0:00:00
                if (seconds <= 1) {
                    running = false;
                    Toast.makeText(getApplicationContext(), "Maximum time reached", Toast.LENGTH_LONG).show();
                }
                // Post code again with delay of one second
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runTabataTimer() {

    }

    private void runFGBTimer() {

    }

    private void runThreeOneTimer() {

    }

    private void runFiveOneTimer() {

    }
}