package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class UserPortalActivity extends Activity {
    private UserSqliteHelper db;
    private SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);

        db = new UserSqliteHelper(this);
    }
    public void onUserPortalActivity(View view) {
            Intent intent = new Intent(this, UserPortalActivity.class);
            startActivity(intent);
    }
}
