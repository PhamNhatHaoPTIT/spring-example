package com.haopn.corepersistence;

import com.haopn.corepersistence.config.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class CorePersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorePersistenceApplication.class, args);
	}

}
