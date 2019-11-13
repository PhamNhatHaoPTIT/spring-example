package com.haopn.demo;

import com.haopn.demo.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	CounterService counterService;

	@PostConstruct
	public void startCounterUpdateSchedule() {
		counterService.resetCounter();
		counterService.setSchedule();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PreDestroy
	public void persis() {
		if(counterService.getCounterRedis() != 0) {
			counterService.persisCounter();
		}
	}

}
