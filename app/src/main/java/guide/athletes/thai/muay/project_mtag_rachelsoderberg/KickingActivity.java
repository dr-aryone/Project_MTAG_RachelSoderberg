package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class KickingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kicking);
        Intent intent = getIntent();
    }
}