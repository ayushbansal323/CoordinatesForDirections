package com.locus.util;

import java.util.ArrayList;
import java.util.List;

import com.locus.pojo.DirLatLng;
import com.locus.pojo.DirectionsOutput;

/**
 * Utility class for controller
 * @author Ayush
 *
 */
public class ControllerUtils 
{
	/**
	 * validates the request
	 * @param pdStartLatitude
	 * @param pdStartLongitude
	 * @param pdEndLatitude
	 * @param pdEndLongitude
	 * @return false if inputs are not correct and true otherwise
	 */
	public static boolean validateDirectionRequest(Double pdStartLatitude, Double pdStartLongitude, Double pdEndLatitude, Double pdEndLongitude)
	{
		if(pdStartLatitude == null || pdStartLongitude == null || pdEndLatitude == null || pdEndLongitude == null)
		{
			return false;
		}
		if(pdStartLatitude > 90 || pdStartLatitude < -90 || pdEndLatitude > 90 || pdEndLatitude < -90  )
		{
			return false;
		}
		if(pdStartLongitude > 180 || pdStartLongitude < -180 || pdEndLongitude > 180 || pdEndLongitude < -180  )
		{
			return false;
		}
		
		return true;
	}

	/**
	 * converts list of DirLatLng to DirectionsOutput
	 * @param plistOutputLatLng list of points(DirLatLng)
	 * @return
	 */
	public static DirectionsOutput convertToOutput(List<DirLatLng> plistOutputLatLng) {
		List<String> llistPoints = new ArrayList<String>();
		for (DirLatLng dirLatLng : plistOutputLatLng) {
			llistPoints.add(dirLatLng.toUrlValue());
		}
		DirectionsOutput lDirectionsOutput = new DirectionsOutput();
		lDirectionsOutput.setPoints(llistPoints);
		return lDirectionsOutput;
	}
}
