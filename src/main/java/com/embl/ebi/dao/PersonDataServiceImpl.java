package com.embl.ebi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.embl.ebi.beans.Person;
import com.embl.ebi.exception.PersonDataServiceException;

/**
 * This class contains the method for Dao operations.
 * 
 * @author hariprasanth.l
 *
 */
@Service
public class PersonDataServiceImpl implements PersonDataService {

	@Autowired
	private PersonDataRepository personDataRepository; 

	@Override
	public List<Person> getPersonData(int from, int size, String sort) {
		List<Person> person = new ArrayList<Person>();
		// Sorting
		Sort sortObject = Sort.by(sort);
		// Pagination
		Pageable paging = PageRequest.of(from, size, sortObject);
		personDataRepository.findAll(paging).forEach(person::add);
		return person;
	}

	@Override
	public Person getById(long personId) throws PersonDataServiceException {
		Optional<Person> person = personDataRepository.findById(personId);
		if (person.isPresent()) {
			return person.get();
		}
		return null;
	}
	
	@Override
	public List<Person> listAll() {
		List<Person> person = new ArrayList<Person>();
		personDataRepository.findAll().forEach(person::add);
		return person;
	}

	@Override
	public List<Person> store(List<Person> person) {
		List<Person> storedPersonData = new ArrayList<Person>();
		personDataRepository.saveAll(person).forEach(storedPersonData::add);
		return storedPersonData;
	}
	
	@Override
	public Person update(Person person) {
		return personDataRepository.save(person);
	}

	@Override
	public void delete(long personId) {
		personDataRepository.deleteById(personId);
	}

	@Override
	public void deleteAll() {
		personDataRepository.deleteAll();
	}

}