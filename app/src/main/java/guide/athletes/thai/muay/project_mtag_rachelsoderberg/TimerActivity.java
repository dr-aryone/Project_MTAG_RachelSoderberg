package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class TimerActivity extends Activity {
    private int seconds = 0; // Number of seconds passed
    private boolean running; // Check whether timer is running
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();

        // Restore activity's state by getting values from Bundle
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer(); // Start stopwatch when method is created
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

    private void runTimer() {
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
