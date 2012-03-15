package com.teamsexy.helloTabs;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geoloqi.android.sdk.service.LQService;
import com.geoloqi.android.sdk.service.LQService.LQBinder;

public class GeofenceActivityBasic extends Activity {
	
	private FelixGeofenceManager geomanager;
	private LQService lqService;
    private boolean bound;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geofence_basic_front);
		
		Button geoA = (Button)findViewById(R.id.doSomething);
		geoA.setOnClickListener(onGeoA);
		
		Intent intent = new Intent(this, LQService.class);
		intent.setAction(LQService.ACTION_DEFAULT);
		intent.putExtra(LQService.EXTRA_SDK_ID, Constants.LQ_SDK_ID);
		intent.putExtra(LQService.EXTRA_SDK_SECRET, Constants.LQ_SDK_SECRET);
		//intent.putExtra(LQService.EXTRA_C2DM_SENDER, Constants.LQ_C2DM_SENDER);
		startService(intent);
		
		geomanager = new FelixGeofenceManager(this);
		
		//Next:
		//lqService.getSession()
		//lqService.getTracker()
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		geomanager.removeLocationUpdates();
		
		// Unbind from LQService
	    if (bound) {
	        unbindService(connection);
	        bound = false;
	    }
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		geomanager.requestLocationUpdates();
		
		Intent intent = new Intent(this, LQService.class);
		bindService(intent, connection, 0);
	}
	
	/* Defines callbacks for service binding, passed to bindService() */
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
	
	private View.OnClickListener onGeoA = new View.OnClickListener() {
		public void onClick(View v) {
			Toast toast = Toast.makeText(GeofenceActivityBasic.this, "Testing", Toast.LENGTH_SHORT);
			toast.show();
		}
	};

}
