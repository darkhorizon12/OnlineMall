package org.juon;

import org.juon.configurations.DownloadServlet;
import org.juon.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAspectJAutoProxy
@EnableScheduling
public class OnlineMallApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OnlineMallApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OnlineMallApplication.class);
	}
	
	@Bean
	public ServletRegistrationBean downloadServlet() {
		ServletRegistrationBean downloadServlet = 
			new ServletRegistrationBean(new DownloadServlet(), "/download/*");
		
		return downloadServlet;
	}
}
