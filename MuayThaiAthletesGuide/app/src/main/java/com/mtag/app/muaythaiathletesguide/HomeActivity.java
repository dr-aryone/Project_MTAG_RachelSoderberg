package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onWorkoutsActivity(View view) {
        Intent intent = new Intent(this, WorkoutsActivity.class);
        startActivity(intent);
    }

    public void onTrainingActivity(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    public void onUserPortalActivity(View view) {
        Intent intent = new Intent(this, UserPortalActivity.class);
        startActivity(intent);
    }

    public void onTimerActivity(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    public void onNagaActivity(View view) {
        Intent intent = new Intent(this, NagaActivity.class);
        startActivity(intent);
    }
}
