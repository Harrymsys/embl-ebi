package com.embl.ebi.utils;

/**
 * @author hariprasanth.l Class to hold the common utils.
 */
public class CommonUtils {

	/**
	 * Method to check the string is empty.
	 * 
	 * @param data holds the input for the check.
	 * @return the boolean - isEmpty
	 */
	public final static boolean isEmpty(String data) {
		if (data != null && data.trim().length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Method to check the Object is empty.
	 * 
	 * @param data holds the input for the check.
	 * @return the boolean - isEmpty
	 */
	public final static boolean isEmptyObject(Object data) {
		if (data != null) {
			return false;
		}
		return true;
	}
	 
	/**
	 * Method to check the data contains only alphabet.
	 * @param data
	 * 		holds the input.
	 * @return
	 * 		the boolean, If all the characters in the input are alphabets.
	 */
	public final static boolean isAlphabet(String data) {
		return data.matches("[a-zA-Z]+");
	}

}
