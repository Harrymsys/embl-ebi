package com.embl.ebi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.embl.ebi.beans.Person;
import com.embl.ebi.constants.PersonDataConstants;
import com.embl.ebi.exception.PersonDataServiceException;

/**
 * @author hariprasanth.l
 * 
 *         This is the controller class which exposes the rest service for the
 *         person data module in EMBL-EBI. This specifically hold the services
 *         for CRUD operations on person module - store / update / fetch / fetch
 *         with search / delete etc.,
 */
@RestController
public class PersonDataController {

	/**
	 * Logger object
	 */
	private final static Logger LOG = LoggerFactory.getLogger(PersonDataController.class);

	/**
	 * This class contains the business logic for the exchange rate module.
	 */
	@Autowired
	PersonDataManagement personDataManagement;

	/**
	 * This service is used to store the person data, which gets validated, then
	 * stored into the datastore.
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the success message after the data is successful loaded to datastore.
	 */
	@PostMapping(path = "/person/store", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> store(@RequestBody List<Person> person) {
		try {
			LOG.info("Storing the person data");
			List<Long> personIds = personDataManagement.store(person);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(PersonDataConstants.PERSON_IDS, personIds);
			String addResponseMessage = String.format("Person data is added successfully to the data store, : %s", dataMap);
			LOG.info(addResponseMessage);
			return ResponseEntity.status(HttpStatus.OK).body(dataMap);
		} catch (PersonDataServiceException e) {
			String errorContent = "Adding the person detail failed due to the error : " + e;
			LOG.error(errorContent);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorContent);
		}
	}
	
	/**
	 * This service is used to update the person data, which gets validated, then
	 * stored into the datastore.
	 * 
	 * 
	 * @param person holds the json input which contains the person data
	 *               informations.
	 * @return the success message after the data is successful updated to datastore.
	 */
	@PutMapping(path = "/person/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> update(@RequestBody Person person) {
		try {
			LOG.info("Updating the person data");
			long personId = personDataManagement.update(person);
			String updateResponseMessage = String.format("Person data is updated successfully to the data store, personId : %s", personId);
			LOG.info(updateResponseMessage);
			return ResponseEntity.status(HttpStatus.OK).body(updateResponseMessage);
		} catch (PersonDataServiceException e) {
			String errorContent = "Updating the person detail failed due to the error : " + e;
			LOG.error(errorContent);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorContent);
		}
	}
	
	/**
	 * This service is used to delete the person data by personId from the datastore.
	 * 
	 * 
	 * @param personId holds the unique id as input.
	 * @return the success message after the data is successful deleted to datastore.
	 */
	@DeleteMapping(path = "/person/delete/{personId}", produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable long personId) {
		try {
			LOG.info("Deleting the person data for the personId : " + personId);
			personDataManagement.delete(personId);
			String deleteResponseMessage = String.format("Person data is deleted successfully from the data store, personId : %s", personId);
			LOG.info(deleteResponseMessage);
			return ResponseEntity.status(HttpStatus.OK).body(deleteResponseMessage);
		} catch (PersonDataServiceException e) {
			String errorContent = "Deleting the person detail failed due to the error : " + e;
			LOG.error(errorContent);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorContent);
		}
	}
	
	/**
	 * This service is used to delete all the person data from the datastore.
	 * 
	 * 
	 * @param personId holds the unique id as input.
	 * @return the success message after the data is successful deleted to datastore.
	 */
	@DeleteMapping(path = "/person/deleteall", produces = "application/json")
	public ResponseEntity<String> deleteall() {
		LOG.info("Deleting all the person data");
		personDataManagement.deleteall();
		String deleteResponseMessage = "All the Person data's are deleted successfully from the data store";
		LOG.info(deleteResponseMessage);
		return ResponseEntity.status(HttpStatus.OK).body(deleteResponseMessage);
	}
	
	
	/**
	 * This service is used to fetch the person data by personId from the datastore.
	 * 
	 * 
	 * @param personId holds the unique id as input.
	 * @return the success message after the data is successful fetched from the datastore.
	 */
	@GetMapping(path = "/person/get/{personId}", produces = "application/json")
	public ResponseEntity<Object> getById(@PathVariable long personId) {
		try {
			LOG.info("Get the person data by personId : " + personId);
			Person person = personDataManagement.getById(personId);
			LOG.info("Received the person data for the personId : " + personId);
			return ResponseEntity.status(HttpStatus.OK).body(person);
		} catch (PersonDataServiceException e) {
			String errorContent = "Fetching the person detail failed due to the error : " + e;
			LOG.error(errorContent);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorContent);
		}
	}
	
	/**
	 * This service is used to fetch the person data by search parameters from the datastore.
	 * 
	 * 
	 * @param personId holds the unique id as input.
	 * @return the success message after the data is successful fetched from the datastore.
	 */
	@GetMapping(path = "/person/get", produces = "application/json")
	public ResponseEntity<Object> getById(@RequestParam(defaultValue = PersonDataConstants.DEFAULT_FROM_VALUE) int from,
			@RequestParam(defaultValue = PersonDataConstants.DEFAULT_RECORDS) int size,
			@RequestParam(defaultValue = PersonDataConstants.DEFAULT_SORT) String sort) {
		try {
			LOG.info("Get the person data from : " + from +", size : " + size + ", sort : " + sort);
			List<Person> personData = personDataManagement.getPersonData(from, size, sort);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(PersonDataConstants.DATA, personData);
			dataMap.put(PersonDataConstants.TOTAL, personData.size());
			LOG.info("Received the person data from : " + from +", size : " + size + ", sort : " + sort);
			return ResponseEntity.status(HttpStatus.OK).body(dataMap);
		} catch (PersonDataServiceException e) {
			String errorContent = "Fetching the person detail failed due to the error : " + e;
			LOG.error(errorContent);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorContent);
		}
	}
	
	
}
