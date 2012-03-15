package com.teamsexy.helloTabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * Based on tutorial from: 
 * http://www.vogella.de/articles/AndroidLocationAPI/article.html#locationapi
 * @author genia / Lars Vogel
 */
public class FelixMapActivity extends MapActivity {
	
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private FelixOverlay itemizedoverlay;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_main); // bind the layout to the activity

		// create a map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		// Either satellite or 2d 
		mapView.setSatellite(true);
		mapController = mapView.getController();
		mapController.setZoom(14); // Zoon 1 is world view
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
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
	}
}