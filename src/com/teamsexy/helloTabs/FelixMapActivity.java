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
import com.google.android.maps.MyLocationOverlay;
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
	
	private MapView mapView;
	private FelixOverlay itemizedoverlay;
	private MapController mapController;
	private LocationManager locationManager;
	private MyLocationOverlay myLocationOverlay;
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_main); // bind the layout to the activity
		
		// Configure the Map
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.add_spot);
		itemizedoverlay = new FelixOverlay(drawable, this);
		
		mapView.setSatellite(true);
		mapController = mapView.getController();
		mapController.setZoom(14); 
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new GeoUpdateHandler());

		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapView.getOverlays().add(myLocationOverlay);

		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapView.getController().animateTo(
						myLocationOverlay.getMyLocation());
			}
		});
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
			createMarker(point, "Test", "Test2");
			mapController.animateTo(point);
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

	private void createMarker(GeoPoint p, String t1, String t2) {
		OverlayItem overlayitem = new OverlayItem(p, t1, t2);
		itemizedoverlay.addOverlay(overlayitem);
		if (itemizedoverlay.size() > 0) {
			mapView.getOverlays().add(itemizedoverlay);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
	}

	@Override
	protected void onPause() {
		super.onResume();
		myLocationOverlay.disableMyLocation();
		myLocationOverlay.disableCompass();
	}
}