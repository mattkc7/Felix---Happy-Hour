package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SpotsActivity extends ListActivity {

	/* Spot database updates */
	private FelixDbAdapter spotDbHelper;
	
	private static final String[] spotNames={"Mordor", 
		"Rivendell", 
		"The Shire",
		"Isengard"};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database helper
        spotDbHelper = new FelixDbAdapter(this);
        spotDbHelper.open();
        
        // Fetch spots data
        getAllSpotsData();
        
        // Set list layout in Spots activity
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, spotNames));
        
    }
    
    /**
     * getAllSpotsData
     * 
     * Retrieve data for all spots from db adapter with a cursor. 
     */
    public void getAllSpotsData() {    	
    	Cursor spotsCursor = spotDbHelper.getAllSpotEntries();
    }
}