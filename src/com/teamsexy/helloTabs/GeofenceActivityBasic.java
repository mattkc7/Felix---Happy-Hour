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
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geofence_basic_front);
		
		Button geoA = (Button)findViewById(R.id.doSomething);
		geoA.setOnClickListener(onGeoA);
		
		geomanager = new FelixGeofenceManager(this);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		geomanager.onActivityPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		geomanager.onActivityResume();
	}
	
	private View.OnClickListener onGeoA = new View.OnClickListener() {
		public void onClick(View v) {
			Toast toast = Toast.makeText(GeofenceActivityBasic.this, "Testing", Toast.LENGTH_SHORT);
			toast.show();
		}
	};

}
