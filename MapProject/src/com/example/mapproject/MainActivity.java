package com.example.mapproject;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends Activity {
	private MapFragment mapFragment;
	private GoogleMap googleMap;

	String bingUrl = "http://dev.virtualearth.net/REST/v1/Traffic/Incidents/";
	String bingKey = "AoHoD_fdpQD73-OoTNnnsGzYu5ClXmVNAGr2t-M_wKbR8TWHqKrZR1X6GHI5pzWm";
	String url2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);

		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition position) {
				LatLngBounds bounds = googleMap.getProjection()
						.getVisibleRegion().latLngBounds;
				if (getAreaInTheScreen(bounds) < 5000000) {
					new ParseTask(googleMap).execute(urlBuilder(bounds));
				} else {
					googleMap.clear();
				}

			}

			private String urlBuilder(LatLngBounds bounds) {
				double northLat = bounds.northeast.latitude;
				double northLong = bounds.northeast.longitude;
				double southLat = bounds.southwest.latitude;
				double southLong = bounds.southwest.longitude;

				url2 = bingUrl + String.valueOf(southLat) + ","
						+ String.valueOf(southLong) + ","
						+ String.valueOf(northLat) + ","
						+ String.valueOf(northLong) + "?key=" + bingKey;
				return url2;

			}

			private float getAreaInTheScreen(LatLngBounds bounds) {
				double northLat = bounds.northeast.latitude;
				double northLong = bounds.northeast.longitude;
				double southLat = bounds.southwest.latitude;
				double southLong = bounds.southwest.longitude;

				Location l = new Location("southwest");
				l.setLatitude(southLat);
				l.setLongitude(southLong);
				Location l2 = new Location("southeast");
				l.setLatitude(southLat);
				l.setLongitude(northLong);
				float length = l.distanceTo(l2);

				Location l3 = new Location("northwest");
				l3.setLatitude(northLat);
				l3.setLongitude(southLong);
				float width = l.distanceTo(l3);

				float area = (Math.abs(width) / 1000)
						* (Math.abs(length) / 1000);

				return area;
			}
		});

	}

}
