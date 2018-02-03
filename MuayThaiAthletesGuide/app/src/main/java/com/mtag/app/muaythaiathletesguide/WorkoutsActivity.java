package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorkoutsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
    }

    public void onWodsActivity(View view) {
        Intent intent = new Intent(this, WodsActivity.class);
        startActivity(intent);
    }

    public void onMartialActivity(View view) {
        Intent intent = new Intent(this, MartialActivity.class);
        startActivity(intent);
    }

    public void onLiftingActivity(View view) {
        Intent intent = new Intent(this, LiftingActivity.class);
        startActivity(intent);
    }
}
