package com.locus.restapi.intf;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.locus.pojo.DirLatLng;

/**
 * Contains all the methods that calls the google maps directions to get the information
 * @author Ayush
 *
 */
public interface DirectionAPI {
	
	/**
	 * gets all the points that are present between the start and end locations
	 * @param pdStartLatitude - start locations latitude
	 * @param pdStartLongitude - start locations longitude
	 * @param pdEndLatitude - end locations latitude
	 * @param pdEndLongitude - end locations latitude
	 * @return all the points between the coordinates
	 * @throws ApiException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public List<DirLatLng> getDirections(double pdStartLatitude, double pdStartLongitude, double pdEndLatitude, double pdEndLongitude) throws ApiException, InterruptedException, IOException;
}
