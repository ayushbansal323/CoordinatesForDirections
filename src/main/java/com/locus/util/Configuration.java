package com.locus.util;

import java.util.Collections;
import java.util.Map;

/**
 * This class contains all the Configuration related to the projects
 * @author Ayush
 *
 */
public class Configuration {
	
	/**
	 * unmodifiable map containing all the ENV variable 
	 */
	private static final Map<String, String> mEnvVars = Collections
            .unmodifiableMap(System.getenv());
	/**
	 * to restrict from creating instance of the class
	 */
	private Configuration() {
		
	}
	
	/**
	 * getter for ENV variables
	 * @return unmodifiable map of ENV variables
	 */
	public static Map<String, String> getEnvVars()
	{
		return mEnvVars;
	}
}
