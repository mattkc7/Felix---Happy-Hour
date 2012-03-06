package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventEditActivity extends ListActivity {

	private FelixDbAdapter eventDbHelper;
	private ListView eventeditview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] placeholder = {"[Edit Event Title]", "[Edit Event About]", "[Event Location]", "[Event Date/Time]", "[Button]"};
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}
}
