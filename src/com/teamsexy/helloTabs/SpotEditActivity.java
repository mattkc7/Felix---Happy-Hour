package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SpotEditActivity extends ListActivity {

	private static final String[] placeholder = {"Where Am I?", "Use My Location", "Add Location Later"};
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}
}
