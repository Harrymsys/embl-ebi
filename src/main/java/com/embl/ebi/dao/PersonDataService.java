package com.embl.ebi.dao;

import java.util.List;

import com.embl.ebi.beans.Person;
import com.embl.ebi.exception.PersonDataServiceException;

/**
 * This interface contains the method for Dao operations.
 * 
 * @author hariprasanth.l
 *
 */
public interface PersonDataService {

	List<Person> store(List<Person> person);

	Person update(Person person);

	Person getById(long personId) throws PersonDataServiceException;
	
	List<Person> getPersonData(int from, int size, String sort);
	
	List<Person> listAll();
	
	void deleteAll();
	
	void delete(long personId);

}
