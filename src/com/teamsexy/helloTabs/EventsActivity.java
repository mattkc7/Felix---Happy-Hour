package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;

public class EventsActivity extends ListActivity {
    
	/* Data management */
	Cursor model = null;
	EventHelper helper = null;
	
	/* various fields from event_detail_form.xml here */
	
	/* Location related */
	private LocationManager locationManager;
	private String locationProvider;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_main);   
    }
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		//helper.close();
		//locationManager.removeUpdates(this);
	}
	
}