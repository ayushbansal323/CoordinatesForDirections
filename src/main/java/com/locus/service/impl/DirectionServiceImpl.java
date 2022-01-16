package com.locus.service.impl;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.locus.pojo.DirLatLng;
import com.locus.restapi.impl.DirectionAPIImpl;
import com.locus.restapi.intf.DirectionAPI;
import com.locus.service.intf.DirectionsService;
import com.locus.util.Constants;
import com.locus.util.DirectionsUtil;

/**
 * implements DirectionService
 * @author Ayush
 *
 */
public class DirectionServiceImpl implements DirectionsService {

	/**
	 * get the directions that are equal distance from each other
	 */
	@Override
	public List<DirLatLng> getDirections(double pdStartLatitude, double pdStartLongitude, double pdEndLatitude,
			double pdEndLongitude) throws ApiException, InterruptedException, IOException 
	{
		// get the directions from google map directions api 
		DirectionAPI lDirectionAPI = new DirectionAPIImpl();
		List<DirLatLng> lListDirLatLngs = lDirectionAPI.getDirections(pdStartLatitude, pdStartLongitude, pdEndLatitude, pdEndLongitude);
		
		// get points equal distance from each other
		List<DirLatLng> lListOutputDirLatLngs = DirectionsUtil.getEqualDistancePoints(lListDirLatLngs,Constants.DISTANCE_BETWEEN_POINTS);
		
		return lListOutputDirLatLngs;
	}

}
