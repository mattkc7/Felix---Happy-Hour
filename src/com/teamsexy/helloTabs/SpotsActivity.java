package com.teamsexy.helloTabs;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

public class SpotsActivity extends ListActivity {

	/* Spot database updates */
	private FelixDbAdapter spotDbHelper;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database helper
        spotDbHelper = new FelixDbAdapter(this);
        spotDbHelper.open();
        
        // Fetch spots data
        getAllSpotsData();
    }
    
    /**
     * getAllSpotsData
     * 
     * Retrieve data for all spots from db adapter with a cursor. 
     */
    public void getAllSpotsData() {    	
    	Cursor spotsCursor = spotDbHelper.getAllSpotEntries();
    	List<String> spotNames = new ArrayList<String>();
    	
    	// If query returns spots, display them in Spots Tab
    	// Might want to add ordering query so that most recent
    	// spots display first...
    	if (spotsCursor.getCount() > 0) {
    		spotsCursor.moveToFirst();
    		while (!spotsCursor.isAfterLast()) {
    			spotNames.add(spotsCursor.getString(1));
    			spotsCursor.moveToLast();
    		}
    		
    	}
    	
    	//...otherwise if cursor is empty, display a "No Spots Found" message.
    	// FIXME clicking on this should take one to the SpotsEdit activity
    	else {
    		spotNames.add("No spots found. Add one?");
    	}
 
    	// Close cursor
    	spotsCursor.close();
    	setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, spotNames));
    }
}