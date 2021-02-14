package com.embl.ebi.exception;

/**
 * @author hariprasanth.l This class represents the common defined exception for
 *         this micro service.
 */
public class PersonDataServiceException extends Exception {

	/**
	 * Unique Id for Serialization.
	 */
	private static final long serialVersionUID = 3069321682393756854L;

	/**
	 * This variable represents the error message.
	 */
	private String errorMessage;

	/**
	 * @param errorMessage represents the error message called via constructor from
	 *                     the caller.
	 */
	public PersonDataServiceException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @param errorMessage represents the error message called via constructor from
	 *                     the caller.
	 */
	public PersonDataServiceException(Exception errorMessage) {
		super(errorMessage.getMessage(), errorMessage.getCause());
		this.errorMessage = errorMessage.toString();
	}

	/**
	 * This toString denotes the exception details.
	 */
	public String toString() {
		return this.errorMessage;
	}
}
