package io.pivotal.workshop.snippet.domain;

import java.util.Date;

public class Snippet {

	private String id;
	private String title;
	private String keywords = "";
	private String description = "";

	private Language lang;
    private String code;

    private Date created;
	private Date modified;

	public Snippet() {
		this.created = new Date();
		this.modified = new Date();
	}

	public Snippet(String title, String keywords, String description, Language lang, String code) {
		this();
		this.title = title;
		this.keywords = keywords;
		this.description = description;
		this.lang = lang;
		this.code = code;
	}

	public Snippet(String title, Language lang, String code) {
		this(title, "", "", lang, code);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

    @Override
    public String toString() {
        return "Snippet{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", lang=" + lang +
                ", code='" + code + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
