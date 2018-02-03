package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SkillsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
    }

    public void onCombosActivity(View view) {
        Intent intent = new Intent(this, CombosActivity.class);
        startActivity(intent);
    }

    public void onStylesActivity(View view) {
        Intent intent = new Intent(this, StylesActivity.class);
        startActivity(intent);
    }

    public void onSelfDefenseActivity(View view) {
        Intent intent = new Intent(this, SelfDefenseActivity.class);
        startActivity(intent);
    }
}
