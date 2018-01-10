package io.pivotal.workshop.snippet.domain;

public class Code {
	private String id;
	private String source;

	public Code() {
		this.id = java.util.UUID.randomUUID().toString().replaceAll("-", "");;
	}

	public Code(String source) {
		this();
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
