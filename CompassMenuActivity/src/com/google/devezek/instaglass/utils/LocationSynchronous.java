package com.google.devezek.instaglass.utils;

import java.util.concurrent.locks.Lock;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationSynchronous implements LocationListener {
	static LocationManager locM;
	static LocationSynchronous singleton = null;
	final static long minTime = 500;
	final static float minDistance = 100.0f;
	private Lock locationGetterLock;
	private Location location = null;
	
	/**
	 * Llamar una vez para inicializar el singleton.
	 */
	public static void Init() {

		// In this example we ask for fine accuracy and require altitude, but
		// these criteria could be whatever you want.
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

		
		String provider = locM.getBestProvider(criteria, true);
		singleton = new LocationSynchronous();
		
		locM.requestLocationUpdates(provider, minTime, minDistance, singleton);
	}

	/**
	 * Devuelve de manera SINCRONA la última localización conocida.
	 * SE BLOQUEA si no hay ninguna localización conocida. 
	 */
	public static Location getLocation() {
		singleton.locationGetterLock.lock();
		Location l = singleton.location;
		singleton.locationGetterLock.unlock();
		if (l == null) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getLocation();
		}
		return l;
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		singleton.locationGetterLock.lock();
		location = arg0;
		singleton.locationGetterLock.unlock();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}
	
	
}
