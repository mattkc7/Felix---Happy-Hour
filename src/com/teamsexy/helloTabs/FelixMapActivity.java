package com.teamsexy.helloTabs;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class FelixMapActivity extends MapActivity {
	
	private MapView felixMap;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
	   
        felixMap = (MapView) findViewById(R.id.mapview);
        felixMap.setBuiltInZoomControls(true);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
}
