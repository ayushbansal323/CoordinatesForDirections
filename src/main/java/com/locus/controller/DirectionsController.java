package com.locus.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.ZeroResultsException;
import com.locus.pojo.DirLatLng;
import com.locus.pojo.DirectionsOutput;
import com.locus.pojo.ErrorResponse;
import com.locus.service.intf.DirectionsService;
import com.locus.util.Constants;
import com.locus.util.ControllerUtils;

/**
 * This controller contains all the directions related api
 * @author Ayush
 * 
 */
@Controller
public class DirectionsController 
{

	@Autowired
	private DirectionsService mDirectionsService;

	/*
	 * This is the api to use to get the directions BASE_URL/directions
	 * @param pdStartLatitude - starting latitude
	 * @param pdStartLongitude - starting longitude
	 * @param pdEndLatitude - end latitude
	 * @param pdEndLongitude - end latitude
	 * @return all the points between start and end 
	 */
	@RequestMapping(path="directions", method = RequestMethod.GET
			, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity singleUseCustomerToken(@RequestParam("startLatitude") Double pdStartLatitude,
												 @RequestParam("startLongitude") Double pdStartLongitude,
												 @RequestParam("endLatitude") Double pdEndLatitude,
												 @RequestParam("endLongitude") Double pdEndLongitude)
	{
		if(ControllerUtils.validateDirectionRequest(pdStartLatitude, pdStartLongitude, pdEndLatitude, pdEndLongitude) == false)
		{
			ErrorResponse lErrorResponse = new ErrorResponse(Constants.BAD_REQUEST, Constants.INVALID_INPUT);
			return ResponseEntity.badRequest().body(lErrorResponse);
		}
		List<DirLatLng> llistOutputLatLng = null;
		try 
		{
			llistOutputLatLng = mDirectionsService.getDirections(pdStartLatitude, pdStartLongitude, pdEndLatitude, pdEndLongitude);
		}catch (ZeroResultsException pException)
		{
			ErrorResponse lErrorResponse = new ErrorResponse(Constants.INTERNAL_SERVER_ERROR, Constants.NO_ROUTES_FOUND);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(lErrorResponse);
		} catch (ApiException | InterruptedException | IOException e) 
		{
			ErrorResponse lErrorResponse = new ErrorResponse(Constants.INTERNAL_SERVER_ERROR, Constants.SOMETHING_WENT_WRONG);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(lErrorResponse);
		}
		
		DirectionsOutput lDirectionsOutput = ControllerUtils.convertToOutput(llistOutputLatLng);
		return ResponseEntity.ok(lDirectionsOutput);
	}
	
}
