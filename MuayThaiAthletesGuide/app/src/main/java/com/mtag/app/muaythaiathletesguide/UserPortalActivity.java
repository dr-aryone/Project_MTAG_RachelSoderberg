package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserPortalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);
    }

    public void onUserPortalActivity(View view) {
        Intent intent = new Intent(this, UserPortalActivity.class);
        startActivity(intent);
    }
}
