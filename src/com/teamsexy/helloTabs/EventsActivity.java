package com.teamsexy.helloTabs;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class EventsActivity extends Activity implements LocationListener {
    
	private LocationManager locationManager;
	private LocationProvider locationProvider;
	private TextView textview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize TextView
        textview = new TextView(this);
        textview.setText("This is the EVENTS tab");
        setContentView(textview);
        
        // Initialize LocationManager
        
    }
	
	/* Activity behavior on startup / pause */
	protected void onResume() {
		super.onResume();
		// State-retrieving activity here
		// Perhaps a feed and/or location update
	}
	
	protected void onPause() {
		super.onPause();
		// State-saving activity here
	}

	/* Location listening functionality */
	public void onLocationChanged(Location location) {
		int latitude = (int) (location.getLatitude());
		int longitude = (int) (location.getLongitude());
		
		textview.setText("New Location: " + latitude + ", " + longitude);
		setContentView(textview);
	}

	// TODO: Provider disabled behavior
	public void onProviderDisabled(String provider) {}

	// TODO: Provider enable behavior
	public void onProviderEnabled(String provider) {}

	// TODO: Placeholder for now
	public void onStatusChanged(String provider, int status, Bundle extras) {}
}