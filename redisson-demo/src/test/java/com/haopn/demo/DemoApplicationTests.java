package com.haopn.demo;

import com.haopn.demo.service.CounterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CounterService counterService;

	@Test
	public void testGetCounter() {
		int number = counterService.getCounterRedis();
		System.out.println(number);
		counterService.increaseCounter();
	}

	@Test
	public void testPersisCounter() {
		counterService.persisCounter();
	}

}
