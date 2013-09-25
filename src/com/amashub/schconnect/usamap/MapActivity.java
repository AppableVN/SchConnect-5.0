package com.amashub.schconnect.usamap;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.amashub.schconnect.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.MapFragment;


public class MapActivity extends Activity {
	
	private static final LatLng NE = new LatLng(50.78559769707147,-53.559348061680794);
	private static final LatLng SW = new LatLng(12.94028814519665,-127.18251172453166);
	private LatLng USA = new LatLng(37.09024,-95.712891);
	private static LatLngBounds MAPBOUNDARY = new LatLngBounds(SW, NE);
	
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private TextView txt_location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		init();
	}

	private void checkBoundaries2(LatLng tempCenter){
		if(MAPBOUNDARY.contains(tempCenter)) return;
		
		double y_center = tempCenter.latitude;
		double x_center = tempCenter.longitude;
		
		double Bound_MaxX = MAPBOUNDARY.northeast.longitude;
		double Bound_MaxY = MAPBOUNDARY.northeast.latitude;
		double Bound_MinX = MAPBOUNDARY.southwest.longitude;
		double Bound_MinY = MAPBOUNDARY.southwest.latitude;
		
		if(x_center < Bound_MinX) x_center = Bound_MinX;
		if(x_center > Bound_MaxX) x_center = Bound_MinX;
		if(y_center < Bound_MinY) y_center = Bound_MinY;
		if(y_center > Bound_MaxY) y_center = Bound_MaxY;
		
		map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(y_center,x_center)));
		Log.e("map","lat/lng:"+ y_center+"/"+x_center);
	}
	
	private void init(){
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();
		txt_location = (TextView)findViewById(R.id.txt_location);
			UiSettings uiSetting = map.getUiSettings();
			uiSetting.setCompassEnabled(false);
			uiSetting.setRotateGesturesEnabled(false);
			uiSetting.setZoomControlsEnabled(false);
			
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(USA, 4));
			
			map.setOnMapClickListener(new OnMapClickListener() {
				
				@Override
				public void onMapClick(LatLng arg0) {
					// TODO Auto-generated method stub
					Log.e("map","lat/lng onMapClick:"+ arg0.latitude+"/"+arg0.longitude);
				}
			});
			map.setOnCameraChangeListener(new OnCameraChangeListener() {
				
				@Override
				public void onCameraChange(CameraPosition arg0) {
					// TODO Auto-generated method stub
					LatLng tempPosition = arg0.target;
					Log.e("map","lat/lng CameraPosition:"+ tempPosition.latitude+"/"+tempPosition.longitude);
					checkBoundaries2(tempPosition);
				}
			});
//		    Marker hamburg = map.addMarker(new MarkerOptions()
//		    				.position(HAMBURG)
//	    					.title("Hamburg"));
//		    Marker kiel = map.addMarker(new MarkerOptions()
//		        			.position(KIEL)
//	        				.title("Kiel")
//	        				.snippet("Kiel is cool"));

		    // Move the camera instantly to hamburg with a zoom of 15.
//		    map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15))
		    // Zoom in, animating the camera.
//		    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}
}
