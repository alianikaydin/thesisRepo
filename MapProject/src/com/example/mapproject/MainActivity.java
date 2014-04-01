package com.example.mapproject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends Activity {
	private MapFragment mapFragment;
	private GoogleMap googleMap;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		
		googleMap.getCameraPosition();
		
		
		//new ParseTask(googleMap).execute();
		//Log.w("a", googleMap.getProjection().getVisibleRegion().toString());
		
	}



	
	 
	
	
	
}
