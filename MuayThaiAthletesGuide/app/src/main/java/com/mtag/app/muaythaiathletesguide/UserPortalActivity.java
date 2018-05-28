package com.mtag.app.muaythaiathletesguide;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UserPortalActivity extends ListActivity  implements android.view.View.OnClickListener{
    Button addButton, getAllButton;
    TextView portal_id;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.addButton)){
            Intent intent = new Intent(this,PortalDetail.class);
            intent.putExtra("portal_id",0);
            startActivity(intent);
        }
        else {
            PortalRepo repo = new PortalRepo(this);

            ArrayList<HashMap<String, String>> portalList =  repo.getPortalList();
            if (portalList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        UserPortalActivity.this.portal_id = (TextView) view.findViewById(R.id.portal_id);
                        String portalId = UserPortalActivity.this.portal_id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),PortalDetail.class);
                        objIndent.putExtra("portal_id", Integer.parseInt( portalId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( UserPortalActivity.this, portalList,
                        R.layout.view_portal_entry, new String[] { "portal_id","inName"},
                        new int[] {R.id.portal_id, R.id.in_name});
                setListAdapter(adapter);
            }
            else{
                Toast.makeText(this,"No content!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        getAllButton = (Button) findViewById(R.id.getAllButton);
        getAllButton.setOnClickListener(this);
    }
}
