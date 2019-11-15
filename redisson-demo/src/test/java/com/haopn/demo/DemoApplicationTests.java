package com.haopn.demo;

import com.haopn.demo.service.CounterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CounterService counterService;

	private RedissonClient client;

	@PostConstruct
	public void init() {
		client = Redisson.create();
	}

	@Test
	public
	void testGetBucket() {
		RBucket<String> fooBucket = client.getBucket("foo");
		fooBucket.set("bar");
	}

	@Test
	public void testLockKey() {
		RBucket<String> fooBucket = client.getBucket("foo");
		RLock lock = client.getLock("lock");
		lock.lock();
		fooBucket.set("bar");
		int x = 1;
		lock.unlock();
	}

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
