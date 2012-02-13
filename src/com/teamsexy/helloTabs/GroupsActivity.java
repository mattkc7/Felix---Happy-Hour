package com.teamsexy.helloTabs;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GroupsActivity extends ListActivity {
	private TextView selection;
	private static final String[] groupNames={"Informatics Dept", "Jedi Knights", 
		"Dumbledore's Army"};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.groups);

        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, groupNames));
    }
    
}