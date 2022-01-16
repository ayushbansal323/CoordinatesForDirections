package com.locus.pojo;

import java.util.Locale;
import java.util.Objects;

/**
 * To define a point on a map using latitude and longitude
 * @author Ayush
 *
 */
public class DirLatLng {

	/** The latitude of this location. */
	private double mdLatitude;

	/** The longitude of this location. */
	private double mdLongitude;

	public double getLatitude() {
		return mdLatitude;
	}

	public void setLatitude(double pdLatitude) {
		this.mdLatitude = pdLatitude;
	}

	public double getLongitude() {
		return mdLongitude;
	}

	public void setLongitude(double pdLongitude) {
		this.mdLongitude = pdLongitude;
	}

	/**
	 * Constructs a location with a latitude/longitude pair.
	 *
	 * @param pdLatitude  The latitude of this location.
	 * @param pdLongitude The longitude of this location.
	 */
	public DirLatLng(double pdLatitude, double pdLongitude) {
		this.mdLatitude = pdLatitude;
		this.mdLongitude = pdLongitude;
	}


	@Override
	public String toString() {
		return toUrlValue();
	}

	public String toUrlValue() {
		// Enforce Locale to English for double to string conversion
		return String.format(Locale.ENGLISH, "%.8f,%.8f", mdLatitude, mdLongitude);
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject)
			return true;
		if (pObject == null || getClass() != pObject.getClass())
			return false;
		DirLatLng latLng = (DirLatLng) pObject;
		return Double.compare(latLng.mdLatitude, mdLatitude) == 0
				&& Double.compare(latLng.mdLongitude, mdLongitude) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mdLatitude, mdLongitude);
	}
}
