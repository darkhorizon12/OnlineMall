package org.juon;

import org.juon.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAspectJAutoProxy
@EnableScheduling
public class OnlineMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMallApplication.class, args);
	}
}
