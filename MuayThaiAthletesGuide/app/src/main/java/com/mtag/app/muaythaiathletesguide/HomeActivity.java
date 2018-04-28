package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// https://stackoverflow.com/questions/18614250/how-to-display-database-content-on-android-listview
// Tutorial on Listview & SQLite: http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html

// Great tutorial: http://hmkcode.com/android-simple-sqlite-database-tutorial/
//         GitHub: https://github.com/hmkcode/Android/tree/master/android-sqlite

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
        Intent intent = new Intent(this, BasicTimerActivity.class); // TimerActivity.class
        startActivity(intent);
    }

    public void onNagaActivity(View view) {
        Intent intent = new Intent(this, NagaActivity.class);
        startActivity(intent);
    }
}
