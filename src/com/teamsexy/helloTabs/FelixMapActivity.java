package com.teamsexy.helloTabs;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;
import org.osmdroid.views.util.constants.MapViewConstants;

import android.app.Activity;
import android.os.Bundle;

public class FelixMapActivity extends Activity implements MapViewConstants {

	private MapView mapView;
	private MapController mapController;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main_template);
     
        mapView = (MapView) this.findViewById(R.id.mapview);  
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        
	    mapView.setBuiltInZoomControls(true);
	    mapView.setMultiTouchControls(true);
	    
	    mapView.getController().setZoom(15);
	    mapView.getController().setCenter(new GeoPoint(51496994, -134733));
	    
	    
	    
	 /*   felixLocationOverlay = new SimpleLocationOverlay(this);                          
	    mapView.getOverlays().add(felixLocationOverlay);
	    
	    felixScaleBarOverlay = new ScaleBarOverlay(this);                          
	    mapView.getOverlays().add(felixScaleBarOverlay);*/
	    
	   
    }
}
