package com.ebml.ebi.constants;

/**
 * This class is used to store the test constants.
 * 
 * @author hariprasanth.l
 *
 */
public class TestConstants {

	/**
	 * PRODUCT_BASE_URL
	 */
	public final static String PERSON_API_BASE_URL = "/person";

	/**
	 * PRODUCT_STORE_URL
	 */
	public final static String PERSON_API_STORE_URL = PERSON_API_BASE_URL + "/store";

	/**
	 * PRODUCT_UPDATE_URL
	 */
	public final static String PERSON_API_UPDATE_URL = PERSON_API_BASE_URL + "/update";

	/**
	 * PRODUCT_GET_URL
	 */
	public final static String PERSON_API_GET_URL = PERSON_API_BASE_URL + "/get";
	
	/**
	 * PRODUCT_DELETE_URL
	 */
	public final static String PERSON_API_DELETE_URL = PERSON_API_BASE_URL + "/delete";
	
	/**
	 * PRODUCT_DELETE_ALL_URL
	 */
	public final static String PERSON_API_DELETE_ALL_URL = PERSON_API_BASE_URL + "/deleteall";

	public static final String PERSON_IDS = "personIds";
	
	public static final String TOTAL = "total";
	
	public static final String DATA = "data";

	public static final String STORE_INPUT_1 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_2 = "[{ \"firstName\": \"Bill\",\"lastName\": \"Gates\", \"age\": \"65\",\"favouriteColour\": \"Red\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Sundar\",\"lastName\": \"Pichai\", \"age\": \"22\",\"favouriteColour\": \"Orange\"}]";

	public static final String STORE_INPUT_3 = "[{ \"firstName\": \"\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_4 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_5 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"\", \"age\": \"1\",\"favouriteColour\": \"\"}]";

	public static final String STORE_INPUT_6 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"Mathew\", \"age\": \"0\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_7 = "[{ \"firstName\": \"Emma123\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_8 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"Emma123\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_9 = "[{ \"firstName\": \"Andrew\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red234\"}]";

	public static final String STORE_INPUT_10 = "[{ \"firstName\": \"ab\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_11 = "[{ \"firstName\": \"AndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrew\","
			+ "\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_12 = "[{ \"firstName\": \"andrew\",\"lastName\": \"ab\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_13 = "[{ \"firstName\": \"andrew\","
			+ "\"lastName\": \"AndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrew\", \"age\": \"1\",\"favouriteColour\": \"Red\"}]";

	public static final String STORE_INPUT_14 = "[{ \"firstName\": \"andrew\",\"lastName\": \"Mathew\", \"age\": \"1\",\"favouriteColour\": \"re\"}]";

	public static final String STORE_INPUT_15 = "[{ \"firstName\": \"andrew\","
			+ "\"lastName\": \"Andrew\", \"age\": \"1\",\"favouriteColour\": \"AndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrewAndrew\"}]";

	public static final String STORE_INPUT_16 = "[{ \"firstName\": \"Bill\",\"lastName\": \"Gates\", \"age\": \"65\",\"favouriteColour\": \"Red\"}, "
			+ "{ \"firstName\": \"Andrew\",\"lastName\": \"ZZZZ\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"BBBBB\",\"lastName\": \"Angela\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"CCCCC\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"yellow\"}, "
			+ "{ \"firstName\": \"DDDDD\",\"lastName\": \"BBBB\", \"age\": \"1\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"EEEEE\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"GGGGG\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Orange\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Steve\",\"lastName\": \"Jobes\", \"age\": \"33\",\"favouriteColour\": \"Black\"}, "
			+ "{ \"firstName\": \"Sundar\",\"lastName\": \"Pichai\", \"age\": \"22\",\"favouriteColour\": \"Orange\"}]";
	
}
