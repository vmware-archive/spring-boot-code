package io.pivotal.workshop.snippet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "snippet")
public class SnippetProperties {

	private String path;
	private String authenticationUri;
	private String authenticationUsername;
	private String authenticationPassword;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public String getAuthenticationUri() {
        return authenticationUri;
    }

    public void setAuthenticationUri(String authenticationUri) {
        this.authenticationUri = authenticationUri;
    }

    public String getAuthenticationUsername() {
        return authenticationUsername;
    }

    public void setAuthenticationUsername(String authenticationUsername) {
        this.authenticationUsername = authenticationUsername;
    }

    public String getAuthenticationPassword() {
        return authenticationPassword;
    }

    public void setAuthenticationPassword(String authenticationPassword) {
        this.authenticationPassword = authenticationPassword;
    }
}
