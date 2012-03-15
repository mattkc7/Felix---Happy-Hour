package com.teamsexy.helloTabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geoloqi.android.sdk.service.LQService;

public class GeofenceActivityBasic extends Activity {

	private FelixGeofenceManager geomanager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geofence_basic_front);
		
		geomanager = new FelixGeofenceManager(this);
		
		Button geo = (Button)findViewById(R.id.doSomething);
		geo.setOnClickListener(onGeo);
		
		Intent intent = new Intent(this, LQService.class);
		intent.putExtra(LQService.EXTRA_SDK_ID, Constants.LQ_SDK_ID);
		intent.putExtra(LQService.EXTRA_SDK_SECRET, Constants.LQ_SDK_SECRET);
		intent.putExtra(LQService.EXTRA_C2DM_SENDER, Constants.LQ_C2DM_SENDER);
		startService(intent);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		// Close helpers / deregister receivers
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		// Close helpers / deregister receivers
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		// Open helpers / deregister receivers
	}
	
	
	private View.OnClickListener onGeo = new View.OnClickListener() {
		public void onClick(View v) {
			Toast toast = Toast.makeText(GeofenceActivityBasic.this, "Testing", Toast.LENGTH_SHORT);
			toast.show();
		}
	};
}
