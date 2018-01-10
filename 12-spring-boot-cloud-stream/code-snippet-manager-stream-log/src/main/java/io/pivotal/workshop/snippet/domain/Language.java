package io.pivotal.workshop.snippet.domain;

public class Language {

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
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", syntax='" + syntax + '\'' +
                '}';
    }
}
