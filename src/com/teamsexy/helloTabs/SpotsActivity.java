package com.teamsexy.helloTabs;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    	spotNames.add("Create a Spot");
    	
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
    	
    	// Close cursor
    	spotsCursor.close();
    	setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, spotNames));
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, SpotEditActivity.class);
        startActivity(i);
    }
    
    
}