package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TabataTimerActivity extends Activity {
    // Add sounds to the media player
    MediaPlayer airhornPlayer;
    MediaPlayer beeppingPlayer; // Unused
    MediaPlayer boxingarenaPlayer; // Unused
    MediaPlayer shipbellPlayer;
    MediaPlayer tingPlayer;

    private int seconds = 0; // Number of seconds passed
    private int timeCap = 0; // Custom max time, stop timer when reached and reset here for countdown
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabata_timer);

        // Start sound operations
        airhornPlayer = MediaPlayer.create(TabataTimerActivity.this, R.raw.airhorn);
        beeppingPlayer = MediaPlayer.create(TabataTimerActivity.this, R.raw.beepping); // Unused
        boxingarenaPlayer = MediaPlayer.create(TabataTimerActivity.this, R.raw.boxingarena); // Unused
        shipbellPlayer = MediaPlayer.create(TabataTimerActivity.this, R.raw.shipbell);
        tingPlayer = MediaPlayer.create(TabataTimerActivity.this, R.raw.ting);

        // Timer Selection Spinner
        Spinner timerSpinner = findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(adapter);
        timerSpinner.setSelection(adapter.getPosition("Tabata"));
        // Spinner click listener
        timerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int timerPos, long id) {
                // Call Timer types when corresponding position is chosen
                switch (timerPos) {
                    case 0: // Basic Stopwatch: Count from 0:00:00 to 99:59:59 (or cap)
                        onDestroy();
                        running = false; // Stop clock
                        timeCap = 6000;
                        startActivity(new Intent(TabataTimerActivity.this, BasicTimerActivity.class));
                        break;
                    case 1: // Countdown: Count from 99:59:59 (or cap) to 0:00:00
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(TabataTimerActivity.this, CountdownTimerActivity.class));
                        break;
                    case 2: // Tabata: Beep every 20th and 30th second. Reset to 0:00:00 on each 30th second
                        onDestroy();
                        running = false;
                        runTabataTimer();
                        break;
                    case 3: // Fight Gone Bad: 17min cap, beep on each minute
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(TabataTimerActivity.this, FGBTimerActivity.class));
                        break;
                    case 4: // "3 On 1 Off": Beep every 3rd and 4th minute
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(TabataTimerActivity.this, ThreeOnTimerActivity.class));
                        break;
                    case 5: // "5 On 1 Off": Beep every 5th and 6th minute
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(TabataTimerActivity.this, FiveOnTimerActivity.class));
                        break;
                    default:
                        running = false;
                        seconds = 0;
                        Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
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
        if (seconds == 0)
            airhornPlayer.start();
        running = true; // Start stopwatch
    }

    public void onClickStop(View view) {
        running = false; // Stop stopwatch
    }

    public void onClickReset(View view) {
        onPause();
        seconds = timeCap; // Reset seconds to zero
    }

    private void runTabataTimer() {
        final TextView timeView = findViewById(R.id.time_view);
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
                    if (seconds == 20) {
                        shipbellPlayer.start();
                    }
                    else if (seconds == 30) {
                        boxingarenaPlayer.start();
                        seconds = 0;
                    }
                }
                // Post code again with delay of one second
                handler.postDelayed(this, 1000);
            }
        });
    }
}