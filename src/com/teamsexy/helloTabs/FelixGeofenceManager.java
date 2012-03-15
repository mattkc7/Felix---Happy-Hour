package com.teamsexy.helloTabs;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class FelixGeofenceManager implements LocationListener {

	private Context context;
	private LocationManager locationManager;
	private String provider;
	
	public FelixGeofenceManager (Context ctx) {
		this.context = ctx;
		
		// Location
		locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
		provider = locationManager.GPS_PROVIDER;
		
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
		}
	}
	
	public void onLocationChanged (Location location) {
		String loc = "Location Changed: " + location.getLatitude() + ", " + location.getLongitude();
		Toast toast = Toast.makeText(context, loc, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void removeLocationUpdates () {
		locationManager.removeUpdates(this);
	}
	
	public void requestLocationUpdates () {
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

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
