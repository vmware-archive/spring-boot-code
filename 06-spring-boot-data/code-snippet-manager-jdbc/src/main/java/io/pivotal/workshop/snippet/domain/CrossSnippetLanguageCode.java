package io.pivotal.workshop.snippet.domain;

public class CrossSnippetLanguageCode {

	private String snippetId;
	private String languageId;
	private String codeId;

	public CrossSnippetLanguageCode() {
	}

	public CrossSnippetLanguageCode(String snippetId, String languageId, String codeId) {

		this.snippetId = snippetId;
		this.languageId = languageId;
		this.codeId = codeId;
	}

	public String getSnippetId() {
		return snippetId;
	}

	public void setSnippetId(String snippetId) {
		this.snippetId = snippetId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

}
