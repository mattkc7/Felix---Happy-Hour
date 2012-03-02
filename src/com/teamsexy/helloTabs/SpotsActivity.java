package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SpotsActivity extends ListActivity {

	private static final String[] spotNames={"Mordor", 
		"Rivendell", 
		"The Shire",
		"Isengard"};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, spotNames));
        
    }
}