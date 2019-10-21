package com.haopn.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TransactionalApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TransactionalApplication.class, args);
		String beans[] = applicationContext.getBeanDefinitionNames();
		for(String x : beans) {
			System.out.println(x);
		}
	}

}
