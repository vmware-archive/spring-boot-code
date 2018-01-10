package io.pivotal.workshop.snippet.client.domain;

public class Snippet {

	private String title;
	private String keywords = "";
	private Language lang;
	private Code code;

	public Snippet() {
	}

	public Snippet(String title, String keywords, String description, Language lang, Code code) {
		this.title = title;
		this.keywords = keywords;
		this.lang = lang;
		this.code = code;
	}

	public Snippet(String title, Language lang, Code code) {
		this(title, "", "", lang, code);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}
}
