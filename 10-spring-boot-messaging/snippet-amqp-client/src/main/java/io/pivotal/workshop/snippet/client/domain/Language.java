package io.pivotal.workshop.snippet.client.domain;

public class Language {

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
}
