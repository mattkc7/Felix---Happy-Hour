package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupEditActivity extends ListActivity {
	
	private FelixDbAdapter groupDbHelper;
	private ListView groupeditview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] placeholder = {"[Group Name]", "[Group About]", "[Other TBD]"}; 
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}

}
