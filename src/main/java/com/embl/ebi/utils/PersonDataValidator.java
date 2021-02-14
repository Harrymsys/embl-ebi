package com.embl.ebi.utils;

import java.util.List;

import com.embl.ebi.beans.Person;
import com.embl.ebi.constants.PersonDataConstants;
import com.embl.ebi.exception.PersonDataServiceException;

/**
 * 
 * @author hariprasanth.l This class is a used to perform the validation for the
 *         person data.
 *
 */
public class PersonDataValidator {

	/**
	 * This method is used to validate the input for the person input data (store /
	 * update).
	 * 
	 * @param person holds the unique input person data.
	 * @throws PersonDataServiceException - If the input data is not matching the
	 *                                    expected condition.
	 */
	private static String validateGeneralInput(Person person) throws PersonDataServiceException {
		String mismatchedInputs = "";
		String firstNameValidation = validateStringField(person.getFirstName(), "firstName");
		String lastNameValidation = validateStringField(person.getLastName(), "lastName");
		String favouriteColourValidation = validateStringField(person.getFavouriteColour(), "favouriteColour");
		String age_Validation = validateIntegerField(person.getAge(), "age");
		if (!CommonUtils.isEmpty(firstNameValidation)) {
			mismatchedInputs = PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR + firstNameValidation + PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		if (!CommonUtils.isEmpty(lastNameValidation)) {
			mismatchedInputs = mismatchedInputs + lastNameValidation + PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		if (!CommonUtils.isEmpty(favouriteColourValidation)) {
			mismatchedInputs = mismatchedInputs + favouriteColourValidation
					+ PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		if (!CommonUtils.isEmpty(age_Validation)) {
			mismatchedInputs = mismatchedInputs + age_Validation + PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		return mismatchedInputs;
	}

	/**
	 * This method is used to check whether the input is empty.
	 * 
	 * @param person holds the unique input person data.
	 * @throws PersonDataServiceException - If the input data is not matching the
	 *                                    expected condition.
	 */
	private static void validateEmptyInput(Person person) throws PersonDataServiceException {
		if (CommonUtils.isEmptyObject(person)) {
			throw new PersonDataServiceException("Person data should be provided in the input");
		}
	}

	/**
	 * This method is used to validate the input for the person input data (store).
	 * 
	 * @param person holds the unique input person data.
	 * @throws PersonDataServiceException - If the input data is not matching the
	 *                                    expected condition.
	 */
	public static void validateStoreInput(List<Person> personList) throws PersonDataServiceException {
		for(Person person : personList) {
			validateEmptyInput(person);
			// If its not an empty data
			String mismatchedInput = "";
			String emptyIdValidation = validateEmptyIdField(person);
			String mismatchedInputValidation = validateGeneralInput(person);
			if (!CommonUtils.isEmpty(emptyIdValidation)) {
				mismatchedInput = emptyIdValidation + PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
			}
			if (!CommonUtils.isEmpty(mismatchedInputValidation)) {
				mismatchedInput = mismatchedInput + mismatchedInputValidation
						+ PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
			}
			if (!CommonUtils.isEmpty(mismatchedInput)) {
				throw new PersonDataServiceException(mismatchedInput);
			}
		}
	}

	/**
	 * This method is used to validate the input for the person input data (Update).
	 * 
	 * @param person holds the unique input person data.
	 * @throws PersonDataServiceException - If the input data is not matching the
	 *                                    expected condition.
	 */
	public static void validateUpdateInput(Person person) throws PersonDataServiceException {
		validateEmptyInput(person);
		// If its not an empty data
		String mismatchedInput = "";
		String personIdValidation = validateIdField(person.getPersonId());
		String mismatchedInputValidation = validateGeneralInput(person);
		if (!CommonUtils.isEmpty(personIdValidation)) {
			mismatchedInput = personIdValidation + PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		if (!CommonUtils.isEmpty(mismatchedInputValidation)) {
			mismatchedInput = mismatchedInput + mismatchedInputValidation
					+ PersonDataConstants.MISMATCH_RESPONSE_SEPERATOR;
		}
		if (!CommonUtils.isEmpty(mismatchedInput)) {
			throw new PersonDataServiceException(mismatchedInput);
		}
	}

	/**
	 * This method is used to validate the input for the person input data (Delete).
	 * 
	 * @param personId holds the unique id for the person data.
	 * @throws PersonDataServiceException - If the input data is not matching the
	 *                                    expected condition.
	 */
	public static void validatePersonIdInput(long personId) throws PersonDataServiceException {
		String mismatchedInput = validateIdField(personId);
		if (!CommonUtils.isEmpty(mismatchedInput)) {
			throw new PersonDataServiceException(mismatchedInput);
		}
	}

	private static String validateStringField(String data, String fieldName) {
		String mismatchedData = "";
		if (CommonUtils.isEmpty(data)) {
			mismatchedData = fieldName + " is not available";
			return mismatchedData;
		}
		if (data.length() < PersonDataConstants.MIN_CHARACTER) {
			mismatchedData = fieldName + " should be greater than or equal to " + PersonDataConstants.MAX_CHARACTER
					+ " characters and should be less than or equal to " + PersonDataConstants.MIN_CHARACTER
					+ " characters";
			return mismatchedData;
		}
		if (data.length() > PersonDataConstants.MAX_CHARACTER) {
			mismatchedData = fieldName + " should be greater than or equal to " + PersonDataConstants.MAX_CHARACTER
					+ " characters and should be less than or equal to " + PersonDataConstants.MIN_CHARACTER
					+ " characters";
			return mismatchedData;
		}
		if (!CommonUtils.isAlphabet(data)) {
			mismatchedData = fieldName + " should contain only alphabets";
			return mismatchedData;
		}
		return mismatchedData;
	}

	private static String validateIntegerField(int age, String fieldName) {
		String mismatchedData = "";
		if (age < 1) {
			mismatchedData = fieldName + " should be greater than or equal to " + PersonDataConstants.MIN_AGE;
			return mismatchedData;
		}
		return mismatchedData;
	}

	private static String validateEmptyIdField(Person person) throws PersonDataServiceException {
		if (person.getPersonId() != 0L) {
			return "PersonId should be empty for storing the person data";
		}
		return "";
	}

	private static String validateIdField(long personId) throws PersonDataServiceException {
		String mismatchedData = "";
		if (personId == 0) {
			return "PersonId is not available";
		}
		return mismatchedData;
	}

	public static void validateSortFieldInput(String sort) throws PersonDataServiceException {
		if (!PersonDataConstants.SORT_ACCEPTED_FIELDS.contains(sort)) {
			throw new PersonDataServiceException("Sort field is not accepted : " + sort + ", Accepted sort fields are "
					+ PersonDataConstants.SORT_ACCEPTED_FIELDS);
		}
	}

}
