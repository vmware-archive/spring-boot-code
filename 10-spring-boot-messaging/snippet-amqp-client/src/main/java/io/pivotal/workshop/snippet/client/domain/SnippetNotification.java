package io.pivotal.workshop.snippet.client.domain;

import java.util.Date;

public class SnippetNotification {

	private String snipetId;
	private String action;
	private Date processed;
	private SnippetError error;

	public SnippetNotification() {
	}

	public SnippetNotification(String snipetId, String action, Date processed, SnippetError error) {
		super();
		this.snipetId = snipetId;
		this.action = action;
		this.processed = processed;
		this.error = error;
	}

	public String getSnipetId() {
		return snipetId;
	}

	public void setSnipetId(String snipetId) {
		this.snipetId = snipetId;
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

	@Override
	public String toString() {
		return "SnippetNotification [snipetId=" + snipetId + ", action=" + action + ", processed=" + processed
				+ ", error=" + error + "]";
	}

}
