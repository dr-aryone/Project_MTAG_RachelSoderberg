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

public class ThreeOnTimerActivity extends Activity {
    // Add sounds to the media player
    MediaPlayer airhornPlayer;
    MediaPlayer beeppingPlayer;
    MediaPlayer boxingarenaPlayer;
    MediaPlayer shipbellPlayer; // Unused
    MediaPlayer tingPlayer;

    private int seconds = 0; // Number of seconds passed
    private int timeCap = 0; // Custom max time, stop timer when reached and reset here for countdown
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_on_timer);

        // Start sound operations
        airhornPlayer = MediaPlayer.create(ThreeOnTimerActivity.this, R.raw.airhorn);
        beeppingPlayer = MediaPlayer.create(ThreeOnTimerActivity.this, R.raw.beepping);
        boxingarenaPlayer = MediaPlayer.create(ThreeOnTimerActivity.this, R.raw.boxingarena);
        shipbellPlayer = MediaPlayer.create(ThreeOnTimerActivity.this, R.raw.shipbell); // Unused
        tingPlayer = MediaPlayer.create(ThreeOnTimerActivity.this, R.raw.ting);

        // Timer Selection Spinner
        Spinner timerSpinner = findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(adapter);
        timerSpinner.setSelection(adapter.getPosition("Sparring: \"3 On 1 Off\""));
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
                        startActivity(new Intent(ThreeOnTimerActivity.this, BasicTimerActivity.class));
                        break;
                    case 1: // Countdown: Count from 99:59:59 (or cap) to 0:00:00
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(ThreeOnTimerActivity.this, CountdownTimerActivity.class));
                        break;
                    case 2: // Tabata: Beep every 20th and 30th second. Reset to 0:00:00 on each 30th second
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(ThreeOnTimerActivity.this, TabataTimerActivity.class));
                        break;
                    case 3: // Fight Gone Bad: 17min cap, beep on each minute
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(ThreeOnTimerActivity.this, FGBTimerActivity.class));
                        break;
                    case 4: // "3 On 1 Off": Beep every 3rd and 4th minute
                        onDestroy();
                        running = false;
                        runThreeOnTimer();
                        break;
                    case 5: // "5 On 1 Off": Beep every 5th and 6th minute
                        onDestroy();
                        running = false;
                        timeCap = 6000;
                        startActivity(new Intent(ThreeOnTimerActivity.this, FiveOnTimerActivity.class));
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

        // Time Cap Selection Spinner
        Spinner timecapSpinner = findViewById(R.id.timecap_spinner);
        ArrayAdapter<CharSequence> capadapter = ArrayAdapter.createFromResource(this,
                R.array.timecap_spinner, android.R.layout.simple_spinner_item);
        capadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timecapSpinner.setAdapter(capadapter);
        timecapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int timeCapPos, long id) {
                // Set time cap to user's selection
                switch(timeCapPos) {
                    case 0: // 60:00
                        running = false;
                        timeCap = 3600;
                        seconds = 0;
                        break;
                    case 1: // 50:00
                        running = false;
                        timeCap = 3000;
                        seconds = 0;
                        break;
                    case 2: // 45:00
                        running = false;
                        timeCap = 2700;
                        seconds = 0;
                        break;
                    case 3: // 40:00
                        running = false;
                        timeCap = 2400;
                        seconds = 0;
                        break;
                    case 4: // 35:00
                        running = false;
                        timeCap = 2100;
                        seconds = 0;
                        break;
                    case 5: // 30:00
                        running = false;
                        timeCap = 1800;
                        seconds = 0;
                        break;
                    case 6: // 29:00
                        running = false;
                        timeCap = 1740;
                        seconds = 0;
                        break;
                    case 7: // 28:00
                        running = false;
                        timeCap = 1680;
                        seconds = 0;
                        break;
                    case 8: // 27:00
                        running = false;
                        timeCap = 1620;
                        seconds = 0;
                        break;
                    case 9: // 26:00
                        running = false;
                        timeCap = 1560;
                        seconds = 0;
                        break;
                    case 10: // 25:00
                        running = false;
                        timeCap = 1500;
                        seconds = 0;
                        break;
                    case 11: // 24:00
                        running = false;
                        timeCap = 1440;
                        seconds = 0;
                        break;
                    case 12: // 23:00
                        running = false;
                        timeCap = 1380;
                        seconds = 0;
                        break;
                    case 13: // 22:00
                        running = false;
                        timeCap = 1320;
                        seconds = 0;
                        break;
                    case 14: // 21:00
                        running = false;
                        timeCap = 1260;
                        seconds = 0;
                        break;
                    case 15: // 20:00
                        running = false;
                        timeCap = 1200;
                        seconds = 0;
                        break;
                    case 16: // 19:00
                        running = false;
                        timeCap = 1140;
                        seconds = 0;
                        break;
                    case 17: // 18:00
                        running = false;
                        timeCap = 1080;
                        seconds = 0;
                        break;
                    case 18: // 17:00
                        running = false;
                        timeCap = 1020;
                        seconds = 0;
                        break;
                    case 19: // 16:00
                        running = false;
                        timeCap = 960;
                        seconds = 0;
                        break;
                    case 20: // 15:00
                        running = false;
                        timeCap = 900;
                        seconds = 0;
                        break;
                    case 21: // 14:00
                        running = false;
                        timeCap = 840;
                        seconds = 0;
                        break;
                    case 22: // 13:00
                        running = false;
                        timeCap = 780;
                        seconds = 0;
                        break;
                    case 23: // 12:00
                        running = false;
                        timeCap = 720;
                        seconds = 0;
                        break;
                    case 24: // 11:00
                        running = false;
                        timeCap = 660;
                        seconds = 0;
                        break;
                    case 25: // 10:00
                        running = false;
                        timeCap = 600;
                        seconds = 0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null && running) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
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

    private void runThreeOnTimer() {
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
                    //}
                    // Beep every 3 and 4 minutes
                    if (seconds > 0 && seconds % 180 == 0) {
                        shipbellPlayer.start();
                    }
                    else if (seconds > 0 && seconds % 240 == 0) {
                        boxingarenaPlayer.start();
                    }
                    else if (seconds >= timeCap) {
                        boxingarenaPlayer.start();
                        onPause();
                    }
                }
                // Don't allow timer to go over 99:59:59
                if (seconds >= 359999) {
                    tingPlayer.start();
                    running = false;
                    Toast.makeText(getApplicationContext(), "Maximum time reached", Toast.LENGTH_LONG).show();
                }
                // Post code again with delay of one second
                handler.postDelayed(this, 1000);
            }
        });
    }

}