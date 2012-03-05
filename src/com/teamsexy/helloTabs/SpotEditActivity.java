package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SpotEditActivity extends ListActivity {

	private FelixDbAdapter spotDbHelper;
	private ListView spoteditview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.spoteditform);
        
        // Initialize view
        String[] placeholder = {"[Edit Spot Title]", "[Edit Spot About]", "[Location]"};
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}
}
