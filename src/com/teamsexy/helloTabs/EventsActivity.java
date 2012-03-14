package com.teamsexy.helloTabs;

import com.teamsexy.helloTabs.GroupsActivity.SpotAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.TextView;

public class EventsActivity extends ListActivity implements LocationListener {
    
	/* Data management */
	Cursor model = null;
	EventHelper helper = null;
	SpotAdapter adapter = null;
	
	/* various fields from event_detail_form.xml here */
	
	/* Location related */
	private LocationManager locationManager;
	private String locationProvider;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Probably need an events main
        setContentView(R.layout.event_detail_form);
        
        helper = new EventHelper(this);
        model=helper.getAll();
        startManagingCursor(model);
        
        // adapter = new SpotAdapter(model);
        // setListAdapter(adapter);
        
        // Initialize LocationManager as GPS_PROVIDER for now
        // In the long term, might want to look at other options to improve battery life
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationProvider = locationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(locationProvider);
        
        // Set Location if it exists
        if (location != null) {
        	
        	Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();
			
			// Genia TODO
        }
    }
	
	/* Activity behavior on startup / pause */
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(locationProvider, 400, 1, this);
	}
	
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		helper.close();
		//locationManager.removeUpdates(this);
	}
	
	// TODO: Provider disabled behavior
	public void onProviderDisabled(String provider) {}

	// TODO: Provider enable behavior
	public void onProviderEnabled(String provider) {}

	// TODO: Placeholder for now
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
}