package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CombosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combos);
        //Intent intent = getIntent(); // TODO: Remove
    }

    public void onBoxingActivity(View view) {
        Intent intent = new Intent(this, BoxingActivity.class);
        startActivity(intent);
    }

    public void onKickingActivity(View view) {
        Intent intent = new Intent(this, KickingActivity.class);
        startActivity(intent);
    }

    public void onAllWeaponsActivity(View view) {
        Intent intent = new Intent(this, AllWeaponsActivity.class);
        startActivity(intent);
    }
}
