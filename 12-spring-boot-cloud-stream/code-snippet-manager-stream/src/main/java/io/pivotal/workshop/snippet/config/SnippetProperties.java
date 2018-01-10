package io.pivotal.workshop.snippet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "snippet")
public class SnippetProperties {

	private String path = "/tmp";
	private String extension = "*.code";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
