package com.teamsexy.helloTabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * Based on tutorial from: 
 * http://www.vogella.de/articles/AndroidLocationAPI/article.html#locationapi
 * @author genia / Lars Vogel
 * 
 * Call with this or similar:
 * startActivity(new Intent([CURRENTACTIVITY].this, FelixMapActivity.class));
 */
public class FelixMapActivity extends MapActivity {
	
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private FelixOverlay itemizedoverlay;
	private GeoPoint homebase;
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		// Commented out to avoid merge conflict.
		//setContentView(R.layout.map_main);

		// Initialize map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		// Set home base in Irvine
		homebase = new GeoPoint(34, -118);
		
		// Overhead view
		mapView.setSatellite(true);
		mapController = mapView.getController();
		mapController.setZoom(14);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new GeoUpdateHandler());

		Drawable drawable = this.getResources().getDrawable(R.drawable.happy_hr_spot);
		itemizedoverlay = new FelixOverlay(drawable);
		createMarker();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
			createMarker();
			mapController.animateTo(point); // mapController.setCenter(point);

		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	private void createMarker() {
		GeoPoint p = new GeoPoint(34, -118);
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
	}
	
	/* Methods to Create */
	public void showAllSpots () {
		// TODO: Show all spots
	}
	
	public void viewSpot () {
		// TODO: Create a spot somewhere
	}
}