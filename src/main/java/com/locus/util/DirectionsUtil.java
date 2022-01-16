package com.locus.util;

import java.util.ArrayList;
import java.util.List;

import com.locus.pojo.DirLatLng;

/**
 * Utility class for Directions Service
 * @author Ayush
 *
 */
public class DirectionsUtil
{

	/**
	 * 
	 * @param pStartDirLatLng - start coordinates 
	 * @param pStopDirLatLng - end coordinates
	 * @return the distance between two coordinates
	 */
	public static double getDistance(DirLatLng pStartDirLatLng, DirLatLng pStopDirLatLng) 
	{
		// The math module contains a function
		// named toRadians which converts from
		// degrees to radians.
		double ldLongitude1 = Math.toRadians(pStartDirLatLng.getLongitude());
		double ldLongitude2 = Math.toRadians(pStopDirLatLng.getLongitude());
		double ldLatitude1 = Math.toRadians(pStartDirLatLng.getLatitude());
		double ldLatitude2 = Math.toRadians(pStopDirLatLng.getLatitude());

		// Haversine formula
		double ldDiffLongitude = ldLongitude2 - ldLongitude1;
		double ldDiffLatitude = ldLatitude2 - ldLatitude1;
		double ldOutput = Math.pow(Math.sin(ldDiffLatitude / 2), 2)
				+ Math.cos(ldLatitude1) * Math.cos(ldLatitude2) * Math.pow(Math.sin(ldDiffLongitude / 2), 2);

		double ldOutputCir = 2 * Math.asin(Math.sqrt(ldOutput));

		// calculate the result
		return (ldOutputCir * Constants.EARTH_RADIUS_METERS);
	}

	/**
	 * 
	 * @param pStartDirLatLng - start coordinates 
	 * @param pStopDirLatLng - end coordinates
	 * @return middle/center coordinates(point) between start and end coordinates
	 */
	public static DirLatLng getMidDirLatLng(DirLatLng pStartDirLatLng, DirLatLng pStopDirLatLng)
	{
		double ldDiffLongitude = Math.toRadians(pStopDirLatLng.getLongitude() - pStartDirLatLng.getLongitude());

		// convert to radians
		double ldLatitude1 = Math.toRadians(pStartDirLatLng.getLatitude());
		double ldLatitude2 = Math.toRadians(pStopDirLatLng.getLatitude());
		double ldLongitude1 = Math.toRadians(pStartDirLatLng.getLongitude());

		double ldBx = Math.cos(ldLatitude2) * Math.cos(ldDiffLongitude);
		double ldBy = Math.cos(ldLatitude2) * Math.sin(ldDiffLongitude);
		double ldLatitudeOutput = Math.atan2(Math.sin(ldLatitude1) + Math.sin(ldLatitude2),
				Math.sqrt((Math.cos(ldLatitude1) + ldBx) * (Math.cos(ldLatitude1) + ldBx) + ldBy * ldBy));
		double ldldLongitudeOutput = ldLongitude1 + Math.atan2(ldBy, Math.cos(ldLatitude1) + ldBx);

		return new DirLatLng(Math.toDegrees(ldLatitudeOutput), Math.toDegrees(ldldLongitudeOutput));

	}

	/**
	 * gets a point at approximate pDistance from Start coordinates towards End coordinates
	 * @param pStartDirLatLng - start coordinates 
	 * @param pStopDirLatLng - end coordinates
	 * @param pDistance - distance from start coordinates 
	 * @return return DirLatLng at pDistance from pStartDirLatLng moving towards pStopDirLatLng
	 */
	public static DirLatLng getPointFromStart(DirLatLng pStartDirLatLng, DirLatLng pStopDirLatLng, int pDistance)
	{
		DirLatLng lMidDirLatLng = getMidDirLatLng(pStartDirLatLng, pStopDirLatLng);
		double lMidDistance = getDistance(pStartDirLatLng, lMidDirLatLng);
		
		if (Math.floor(lMidDistance) == Constants.ZERO) {
			return lMidDirLatLng;
		}
		
		if (Math.ceil(lMidDistance) == pDistance || Math.floor(lMidDistance) == pDistance) {
			return lMidDirLatLng;
		} else if (Math.ceil(lMidDistance) < pDistance) {
			int liNewDistance = (int) (pDistance - Math.ceil(lMidDistance));
			return getPointFromStart(lMidDirLatLng, pStopDirLatLng, liNewDistance);
		} else {
			return getPointFromStart(pStartDirLatLng, lMidDirLatLng, pDistance);
		}
	}

	/**
	 * get approximately equal distance points generated from the list of DirLatLng that are arranged from start location to end location
	 * @param plistDirLatLngs list of coordinates from start locations to end locations respectively
	 * @param pDistance - distance on how much distance a coordinate should be
	 * @return
	 */
	public static List<DirLatLng> getEqualDistancePoints(List<DirLatLng> plistDirLatLngs, int pDistance)
	{
		List<DirLatLng> llistDirLatLngs = new ArrayList<DirLatLng>();
		if (plistDirLatLngs.size() == Constants.ZERO) {
			return llistDirLatLngs;
		}
		
		llistDirLatLngs.add(plistDirLatLngs.get(0));
		if (plistDirLatLngs.size() == 1) {
			return llistDirLatLngs;
		}
		
		double ldDistanceCrossed = Constants.ZERO;
		for (int i = 1; i < plistDirLatLngs.size(); i++)
		{
			// get distance between current and last coordinate
			double lDistanceBetweenLngLog = getDistance(plistDirLatLngs.get(i), plistDirLatLngs.get(i - 1));
			
			// if the distance is approximate to distance then add it to the list
			if (Math.ceil(ldDistanceCrossed + lDistanceBetweenLngLog) == pDistance
					|| Math.floor(ldDistanceCrossed + lDistanceBetweenLngLog) == pDistance) 
			{
				llistDirLatLngs.add(plistDirLatLngs.get(i));
				ldDistanceCrossed = Constants.ZERO;
			}
			// if the distance is greater than the distance then point that are needed to be added lies between the coordinates
			else if (Math.ceil(ldDistanceCrossed + lDistanceBetweenLngLog) > pDistance) 
			{
				DirLatLng lStartDirLatLng = plistDirLatLngs.get(i - 1);
				DirLatLng lEndDirLatLng = plistDirLatLngs.get(i);
				// find and add all the points that lies between the ith and ith-1 coordinate
				do {
					DirLatLng lDirLatLng = getPointFromStart(lStartDirLatLng, lEndDirLatLng,
							(int) (pDistance - ldDistanceCrossed));
					llistDirLatLngs.add(lDirLatLng);
					lDistanceBetweenLngLog = (Math.ceil(ldDistanceCrossed + lDistanceBetweenLngLog) - pDistance);
					lStartDirLatLng = lDirLatLng;
					ldDistanceCrossed = 0;
				} while (lDistanceBetweenLngLog > pDistance);
				ldDistanceCrossed = (int) Math.ceil(lDistanceBetweenLngLog);
			} 
			// the distance is less tha pDistance so no point lies in between ith and ith-1 coordinates so add it to the distance from last coordinate
			else 
			{
				ldDistanceCrossed = ldDistanceCrossed + lDistanceBetweenLngLog;
			}
		}
		// if last coordinate is not add add it 
		if (ldDistanceCrossed != Constants.ZERO) 
		{
			llistDirLatLngs.add(plistDirLatLngs.get(plistDirLatLngs.size() - 1));
		}
		return llistDirLatLngs;
	}

}
