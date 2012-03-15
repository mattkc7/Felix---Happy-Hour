package com.teamsexy.helloTabs;

import com.geoloqi.android.sdk.service.LQService;
import com.geoloqi.android.sdk.service.LQService.LQBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

/**
 * FelixGeofenceManager
 * @author genia
 * 
 * Location-tracking services
 * Geoloqi Geofencing API
 */
public class FelixGeofenceManager implements LocationListener {

	private Context context;
	
	// Location
	private LocationManager locationManager;
	private String provider;
	
	// Geofence
	private LQService lqService;
    private boolean bound;
	
	public FelixGeofenceManager (Context ctx) {
		this.context = ctx;
		
		// Location
		locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
		provider = locationManager.GPS_PROVIDER;
		
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
		}
		
		// Geofence API
		Intent intent = new Intent(context, LQService.class);
		intent.setAction(LQService.ACTION_DEFAULT);
		intent.putExtra(LQService.EXTRA_SDK_ID, Constants.LQ_SDK_ID);
		intent.putExtra(LQService.EXTRA_SDK_SECRET, Constants.LQ_SDK_SECRET);
		//intent.putExtra(LQService.EXTRA_C2DM_SENDER, Constants.LQ_C2DM_SENDER);
		context.startService(intent);
		
		//Next:
		//lqService.getSession()
		//lqService.getTracker()
	}
	
	public void onLocationChanged (Location location) {
		Toast.makeText(context, 
				"Location Changed: " + location.getLatitude() + ", " + location.getLongitude(), 
				Toast.LENGTH_SHORT).show();
	}
	
	public void removeLocationUpdates () {
		locationManager.removeUpdates(this);
	}
	
	public void requestLocationUpdates () {
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}
	
	public void onActivityPause () {
		// Location
		removeLocationUpdates();
		
		// Geofencing
		if (bound) {
	        context.unbindService(connection);
	        bound = false;
	    }
	}
	
	public void onActivityResume () {
		// Location
		requestLocationUpdates();
		
		Intent intent = new Intent(context, LQService.class);
		context.bindService(intent, connection, 0);
	}
	
	/* Defines callbacks for service binding, passed to bindService() */
	// ISSUE: It seems this service connection is leaking. fixing.
	private ServiceConnection connection = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder service) {
	        try {
	            // We've bound to LocalService, cast the IBinder and get LocalService instance.
	            LQBinder binder = (LQBinder) service;
	            lqService = binder.getService();
	            bound = true;
	        } catch (ClassCastException e) {
	            // Pass
	        }
	    }
	 
	    @Override
	    public void onServiceDisconnected(ComponentName name) {
	        bound = false;
	    }
	};

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(context, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
