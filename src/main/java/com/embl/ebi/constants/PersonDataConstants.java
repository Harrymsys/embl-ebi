package com.embl.ebi.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to hold the constants for the person data service.
 * 
 * @author hariprasanth.l
 *
 */
public class PersonDataConstants {
	
	public final static String MISMATCH_RESPONSE_SEPERATOR = "\n";
	
	public static final int MAX_CHARACTER = 60;
	
	public static final int MIN_CHARACTER = 3;
	
	public static final int MIN_AGE = 1;

	public static final String DEFAULT_SEARCH_WORDS = "";

	public static final String DEFAULT_RECORDS = "1000";
	
	public static final String DEFAULT_FROM_VALUE = "0";
	
	public static final String DEFAULT_SORT = "firstName";

	public static final List<String> SORT_ACCEPTED_FIELDS = fetchSortList();

	public static final String PERSON_IDS = "personIds";

	public static final String DATA = "data";
	
	public static final String TOTAL = "total";
	
	private static List<String> fetchSortList() {
		List<String> sortList = new ArrayList<String>();
		sortList.add("firstName");
		sortList.add("lastName");
		sortList.add("favouriteColour");
		sortList.add("age");
		return sortList;
	}

}
