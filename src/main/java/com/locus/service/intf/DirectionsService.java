package com.locus.service.intf;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.locus.pojo.DirLatLng;

/**
 * Service containing all the business logic related to the directions
 * @author Ayush
 *
 */
public interface DirectionsService 
{
	/**
	 * get the directions that are equal distance from each other
	 * @param pdStartLatitude - start locations latitude
	 * @param pdStartLongitude - start locations longitude
	 * @param pdEndLatitude - end locations latitude
	 * @param pdEndLongitude - end locations latitude
	 * @return all the points equal distance between the coordinates
	 * @throws ApiException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public List<DirLatLng> getDirections(double pdStartLatitude, double pdStartLongitude, double pdEndLatitude, double pdEndLongitude) throws ApiException, InterruptedException, IOException;
}
