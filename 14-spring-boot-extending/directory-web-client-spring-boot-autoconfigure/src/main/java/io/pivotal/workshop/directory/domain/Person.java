package io.pivotal.workshop.directory.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {


    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    private String id;
    private String email;
    private String name;
    private String password;
    private String role = "USER";
    private boolean enabled = true;
    private Date birthday;
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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", birthday=" + birthday +
                ", date=" + date +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
