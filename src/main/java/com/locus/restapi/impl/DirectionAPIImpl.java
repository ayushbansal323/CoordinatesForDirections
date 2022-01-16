package com.locus.restapi.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.locus.pojo.DirLatLng;
import com.locus.restapi.intf.DirectionAPI;
import com.locus.util.Constants;
import com.locus.util.Configuration;

/**
 * Contains all the methods that calls the google maps directions to get the information
 * @author Ayush
 *
 */
public class DirectionAPIImpl implements DirectionAPI
{
	/**
	 * gets all the points that are present between the start and end locations
	 * these points are not at equal distance but helps to navigate to the locations 
	 */
	public List<DirLatLng> getDirections(double pdStartLatitude, double pdStartLongitude, double pdEndLatitude, double pdEndLongitude) throws ApiException, InterruptedException, IOException
	{
		// getting the api_key
		String lstrApiKey = Configuration.getEnvVars().get(Constants.API_KEY);
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(lstrApiKey)
			    .build();
		String lstrOrigin 	  = pdStartLatitude + Constants.COMMA + pdStartLongitude;
		String lstDestination = pdEndLatitude + Constants.COMMA + pdEndLongitude;
		DirectionsResult lDirectionsResult = DirectionsApi.getDirections(context, lstrOrigin, lstDestination).mode(TravelMode.WALKING).await();
		List<DirLatLng> lListDirLatLngs = getDirectionsFromResult(lDirectionsResult);
		return lListDirLatLngs;
	}
	
	/**
	 * getting all the points present in the steps of the result
	 * @param lDirectionsResult
	 * @return
	 */
	private List<DirLatLng> getDirectionsFromResult(DirectionsResult lDirectionsResult)
	{
		List<DirLatLng> lListDirLatLngs = new ArrayList<DirLatLng>();
		if(lDirectionsResult.routes.length == 0)
		{
			return lListDirLatLngs;
		}
		DirectionsLeg[] lArrDirectionsLeg  = lDirectionsResult.routes[0].legs;
		// getting all the points from polyline from the steps of each leg and combining it
		// as polyline from each steps gives better representations of the points between the route
		for (DirectionsLeg lDirectionsLeg : lArrDirectionsLeg) {
			for (DirectionsStep lDirectionStep : lDirectionsLeg.steps)
			{
				List<LatLng> lListLatLang = lDirectionStep.polyline.decodePath();
				for (LatLng lLatLng : lListLatLang) {
					lListDirLatLngs.add(new DirLatLng(lLatLng.lat, lLatLng.lng));
				}
			}
		}
		return lListDirLatLngs;
	}
}
