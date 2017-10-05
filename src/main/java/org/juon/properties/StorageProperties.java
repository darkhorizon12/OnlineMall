package org.juon.properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class StorageProperties {
	private String location;

	@Autowired
	public StorageProperties(ServletContext servletContext) {
		location = servletContext.getRealPath("/") + "upload-dir";
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
