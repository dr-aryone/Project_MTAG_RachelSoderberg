package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }

    public void onTechniquesActivity(View view) {
        Intent intent = new Intent(this, TechniquesActivity.class);
        startActivity(intent);
    }

    public void onCombosActivity(View view) {
        Intent intent = new Intent(this, CombosActivity.class);
        startActivity(intent);
    }

    public void onVideosActivity(View view) {
        Intent intent = new Intent(this, VideosActivity.class);
        startActivity(intent);
    }
}
