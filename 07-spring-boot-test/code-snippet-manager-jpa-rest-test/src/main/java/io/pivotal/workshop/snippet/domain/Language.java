package io.pivotal.workshop.snippet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Language {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private String syntax = "text";

	public Language() {
	}

	public Language(String name) {
		this();
		this.name = name;
	}

	public Language(String name, String syntax) {
		this(name);
		this.syntax = syntax;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	@Override
	public boolean equals(Object obj) {
		Language that = (Language) obj;

		if (this.getName().equals(that.getName()) && this.getSyntax().equals(that.getSyntax())
				&& this.getId().equals(that.getId()))
			return true;

		return false;
	}

}
