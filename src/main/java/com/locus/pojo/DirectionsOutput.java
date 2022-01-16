package com.locus.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is output off the GET /directions api
 * @author Ayush
 */
public class DirectionsOutput {

	/** list of all the points */
	@JsonProperty("points")
	private List<String> mlistPoints= new ArrayList<String>();

	public List<String> getPoints() {
		return mlistPoints;
	}

	public void setPoints(List<String> plistPoints) {
		this.mlistPoints = plistPoints;
	}

	@Override
	public String toString() {
		return "DirectionsOutput [mlistPoints=" + mlistPoints + "]";
	}
	
	
}
