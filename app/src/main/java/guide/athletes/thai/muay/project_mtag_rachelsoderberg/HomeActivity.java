package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

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

    public void onSkillsActivity(View view) {
        Intent intent = new Intent(this, SkillsActivity.class);
        startActivity(intent);
    }

    public void onWorkoutsActivity(View view) {
        Intent intent = new Intent(this, WorkoutsActivity.class);
        startActivity(intent);
    }

    public void onUserPortalActivity(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
