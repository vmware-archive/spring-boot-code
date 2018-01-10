package io.pivotal.workshop.directory.error;

import java.util.Date;
import java.util.List;

import org.springframework.validation.ObjectError;

public class PersonError {

	private List<ObjectError> errors;
	private Date date;
	
	public PersonError(){
		this.date = new Date();
	}
	
	public PersonError(List<ObjectError> errors){
		this();
		this.errors = errors;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
