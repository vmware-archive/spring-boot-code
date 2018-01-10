package io.pivotal.workshop.snippet.domain;

import java.util.Date;

public class SnippetNotification {

	private String snippetId;
	private String action;
	private Date processed;
	private SnippetError error;

	public SnippetNotification() {
	}

	public SnippetNotification(String snippetId, String action, Date processed, SnippetError error) {
		super();
		this.snippetId = snippetId;
		this.action = action;
		this.processed = processed;
		this.error = error;
	}

	public String getSnippetId() {
		return snippetId;
	}

	public void setSnippetId(String snippetId) {
		this.snippetId = snippetId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getProcessed() {
		return processed;
	}

	public void setProcessed(Date processed) {
		this.processed = processed;
	}

	public SnippetError getError() {
		return error;
	}

	public void setError(SnippetError error) {
		this.error = error;
	}

}
