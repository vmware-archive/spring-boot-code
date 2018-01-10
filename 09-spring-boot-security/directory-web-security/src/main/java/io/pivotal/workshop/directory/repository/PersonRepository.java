package io.pivotal.workshop.directory.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.directory.domain.Person;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person,String>{

	public Person findByEmailIgnoreCase(@Param("email") String email);
}
