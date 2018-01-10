package io.pivotal.workshop.directory.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;

import io.pivotal.workshop.directory.annotation.Audit;
import io.pivotal.workshop.directory.domain.Person;

@Repository
public class DirectoryRepository {
	
	
	@SuppressWarnings("serial")
	private List<Person> directory = new ArrayList<Person>(){{
		add(new Person("john@email.com","John S","password","1985-11-10"));
		add(new Person("mike@email.com","Mike H","password","1984-12-02"));
		add(new Person("dan@email.com","Dan B","password","1983-03-07"));
		add(new Person("bill@email.com","Bill G","password","1983-06-12"));
		add(new Person("mark@email.com","Mark S","password","1986-02-22"));
	}};
	
	public Iterable<Person> findAll(){
		return this.directory;
	}
	
	// SOLUTION:
	@Audit
	public Optional<Person> findByEmail(String email){
		return findFirstBy( p -> p.getEmail().equals(email));
	}
	
	public Optional<Person> findById(String id){
		return findFirstBy( p -> p.getId().equals(id));
	}
	
	public Person save(Person person){
		Optional<Person> result = this.findById(person.getId());
		
		if(result.isPresent()){
			
			if( person.getEmail() != null) result.get().setEmail(person.getEmail());
			if( person.getName() != null) result.get().setName(person.getName());
			if( person.getPassword() != null) result.get().setPassword(person.getPassword());
			if( person.getBirthday() != null) result.get().setBirthday(person.getBirthday());
			result.get().setModified(new Date());
			
			return result.get();
			
		}else{
			
			if (person.getId() != null){
				person.setId(UUID.randomUUID().toString().replaceAll("-",""));
			}
			
			person.setCreated(new Date());
			person.setModified(new Date());
			this.directory.add(person);
			
			return person;
		}
	}
	
	public void delete(String id){
		this.findById(id).ifPresent(p -> { this.directory.remove(p); }); 
	}
	
	private Optional<Person> findFirstBy(Predicate<Person> findBy){
		return directory.stream()
		.filter(findBy)
		.findFirst();
	}
	
}
