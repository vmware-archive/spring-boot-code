package io.pivotal.workshop.snippet.domain;

public class SnippetError {

	private String message;
	private String errorCode;

	public SnippetError() {
	}

	public SnippetError(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
