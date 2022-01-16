package com.locus.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.ZeroResultsException;
import com.locus.pojo.DirLatLng;
import com.locus.service.impl.DirectionServiceImpl;
import com.locus.service.intf.DirectionsService;
import com.locus.util.ControllerUtils;

/*
 * This is the main program to run the code as JAVA application if do not want to run it on tomcat
 */
public class Main {

	private static DirectionsService mDirectionsService = new DirectionServiceImpl();
	
	public static void main(String[] args) {
		
		Scanner lScanner = new Scanner(System.in);
		// declaring variable 
		Double ldStartLatitude;
		Double ldStartLongitude;
		Double ldEndLatitude;
		Double ldEndLongitude;
		List<DirLatLng> llistPoints;
		
		while (true) {
			// taking input
			System.out.print("Enter startLatitude :: ");
			ldStartLatitude = lScanner.nextDouble();
			
			System.out.print("Enter startLongitude :: ");
			ldStartLongitude = lScanner.nextDouble();

			System.out.print("Enter endLatitude :: ");
			ldEndLatitude = lScanner.nextDouble();
			
			System.out.print("Enter endLongitude :: ");
			ldEndLongitude = lScanner.nextDouble();
			
			try {
				if(ControllerUtils.validateDirectionRequest(ldStartLatitude, ldStartLongitude, ldEndLatitude, ldEndLongitude) == false)
				{
					System.out.println("Invalid Input");
					continue;
				}
				
				llistPoints = mDirectionsService.getDirections(ldStartLatitude, ldStartLongitude, ldEndLatitude, ldEndLongitude);
				
				//printing output
				System.out.println("points :: ");
				for (DirLatLng lPoint : llistPoints) 
				{
					System.out.println(lPoint);
				}
				System.out.println();
			} 
			catch (ZeroResultsException pException)
			{
				System.out.println("No path found");
			}
			catch (ApiException | InterruptedException | IOException pException) 
			{
				System.out.println("Someting went Wrong");
				pException.printStackTrace();
			};
			
		}
	}
}
