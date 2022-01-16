package com.locus.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response for the api
 * @author Ayush
 *
 */
public class ErrorResponse {

	/** Error to be send */
	@JsonProperty("error")
	private String mstrError;

	/** Error Message to be send */
	@JsonProperty("message")
	private String mstrMessage;

	public ErrorResponse(String pstrError, String pstrMessage) {
		this.mstrError = pstrError;
		this.mstrMessage = pstrMessage;
	}

	public String getError() {
		return mstrError;
	}

	public void setError(String pstrError) {
		this.mstrError = pstrError;
	}

	public String getMessage() {
		return mstrMessage;
	}

	public void setMessage(String pstrMessage) {
		this.mstrMessage = pstrMessage;
	}
	
	
}
