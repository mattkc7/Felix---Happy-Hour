package com.teamsexy.helloTabs;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.geoloqi.android.sdk.LQException;
import com.geoloqi.android.sdk.LQSession;
import com.geoloqi.android.sdk.LQSession.OnRunApiRequestListener;
import com.geoloqi.android.sdk.LQTracker;
import com.geoloqi.android.sdk.service.LQService;
import com.geoloqi.android.sdk.service.LQService.LQBinder;

/**
 * FelixGeofenceManager
 * @author genia
 * 
 * Location-tracking services
 * Geoloqi Geofencing API
 */
public class FelixGeofenceManager implements LocationListener {
	public static final String TAG = "Geofencing";
	
	private Context context;
	LQSession fsession;
	LQTracker ftracker;
	
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
	
	public void geoloqi() {
		
		
		if (lqService != null) {
			Toast.makeText(context, "Sup gurl", Toast.LENGTH_SHORT).show();
			
		    LQSession session = lqService.getSession();
		 
		    // Build your request
		    HttpGet request = new HttpGet();
		    try {
		        request.setURI(new URI("https://api.geoloqi.com/1/account/profile"));
		        // TODO
		    
		    } catch (URISyntaxException e) {
		   
		    	Toast.makeText(context, "You fail, ho", Toast.LENGTH_SHORT);
		    }
		 
		    // Send the request
		    session.runAPIRequest(request, new OnRunApiRequestListener() {
		        public void onRunAPIRequest(HttpResponse response, LQException e) {
		            if (e != null) {
		                // Oops, an Exception was thrown!
		                Log.e(TAG, e.getMessage());
		            } else {
		            	Toast.makeText(context, "party time", Toast.LENGTH_SHORT).show();
		            }
		        }

				@Override
				public void onComplete(HttpResponse arg0) {
					Toast.makeText(context, "party time complete", Toast.LENGTH_SHORT).show();
					
				}

				@Override
				public void onFailure(LQSession arg0, LQException arg1) {
					// TODO Auto-generated method stub
					
				}
		    });
		}
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
