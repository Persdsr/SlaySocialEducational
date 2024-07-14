package com.slay.Slay;

import com.slay.Slay.POJO.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class SlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlayApplication.class, args);
	}

}
