package io.pivotal.workshop.directory.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Person {

	@Transient
	private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@Column(unique = true)
	private String email;
	private String name;
	private String password;
	private String role = "USER";
	private boolean enabled = true;
	private Date birthday;

	@Column(insertable = true, updatable = false)
	private Date created;
	private Date modified;

	public Person() {
		this.created = new Date();
		this.modified = new Date();
	}

	public Person(String email, String name, String password, String birthday) {
		this();
		this.email = email;
		this.name = name;
		this.password = password;

		try {
			this.birthday = date.parse(birthday);
		} catch (ParseException e) {
			this.birthday = null;
		}
	}

	public Person(String email, String name, String password, Date birthday) {
		this();
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
	}

	public Person(String email, String name, String password, String birthday, String role, boolean enabled) {
		this(email, name, password, birthday);
		this.role = role;
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreated() {
		return created;
	}

	public Date getModified() {
		return modified;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@PrePersist
	void onCreate() {
		this.created = new Date();
		this.modified = new Date();
	}

	@PreUpdate
	void onUpdate() {
		this.modified = new Date();
	}
}
