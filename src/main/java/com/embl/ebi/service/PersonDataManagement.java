package com.embl.ebi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.embl.ebi.beans.Person;
import com.embl.ebi.dao.PersonDataServiceImpl;
import com.embl.ebi.exception.PersonDataServiceException;
import com.embl.ebi.utils.CommonUtils;
import com.embl.ebi.utils.PersonDataValidator;

/**
 * This class is used to hold the implementation for the exchange rate. It is
 * the primary layer for the Http / Https service.
 * 
 * @author hariprasanth.l
 *
 */
@Component("personDataManagement")
public class PersonDataManagement {

	@Autowired
	private PersonDataServiceImpl personDataService;

	/**
	 * This business layer is used to store the person data, which gets validated,
	 * then stored into the datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the person id after the data is successful loaded to datastore.
	 */
	public List<Long> store(List<Person> person) throws PersonDataServiceException {
		PersonDataValidator.validateStoreInput(person);
		List<Person> storedPersonData = personDataService.store(person);
		return storedPersonData.stream().map(persondata -> persondata.getPersonId()).collect(Collectors.toList());
	}

	/**
	 * This business layer is used to update the person data, which gets validated,
	 * then stored into the datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the person id after the data is successful updated to datastore.
	 */
	public long update(Person person) throws PersonDataServiceException {
		PersonDataValidator.validateUpdateInput(person);
		validateAndGetById(person.getPersonId());
		Person storedPersonData = personDataService.update(person);
		return storedPersonData.getPersonId();
	}

	/**
	 * This business layer is used to delete the person data by id from the
	 * datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the person id after the data is successful deleted to datastore.
	 */
	public void delete(long personId) throws PersonDataServiceException {
		PersonDataValidator.validatePersonIdInput(personId);
		personDataService.delete(personId);
	}

	/**
	 * This business layer is used to get the person data by id from the datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the person after the data is successful fetched from the
	 *         datastore.
	 */
	public Person getById(long personId) throws PersonDataServiceException {
		PersonDataValidator.validatePersonIdInput(personId);
		return validateAndGetById(personId);
	}

	/**
	 * This business layer is used to get the person data by search criteria from
	 * the datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the List<person> after the data is successful fetched from the
	 *         datastore.
	 */
	public List<Person> getPersonData(int from, int size, String sort) throws PersonDataServiceException {
		PersonDataValidator.validateSortFieldInput(sort);
		return personDataService.getPersonData(from, size, sort);
	}

	/**
	 * Method to fetch by id
	 */
	private Person validateAndGetById(long personId) throws PersonDataServiceException {
		Person person = personDataService.getById(personId);
		if (CommonUtils.isEmptyObject(person)) {
			throw new PersonDataServiceException("Person Data is not available for the id : " + personId);
		}
		return person;
	}

	public void deleteall() {
		personDataService.deleteAll();
	}

}
