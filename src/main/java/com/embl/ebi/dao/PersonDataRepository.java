package com.embl.ebi.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.embl.ebi.beans.Person;

/**
 * @author hariprasanth.l This is an interface specific to Person Data source.
 */
@Repository("personDataRepository")
public interface PersonDataRepository extends CrudRepository<Person, Long> {

	/**
	 * Fetch with sorting and pagination
	 */
	Page<Person> findAll(Pageable pageables);

}
