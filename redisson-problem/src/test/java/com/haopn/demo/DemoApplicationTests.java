package com.haopn.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	RedissonClient redissonClient;

	@Test
	public
	void testGetBucket() {
		RBucket<String> fooBucket = redissonClient.getBucket("foo");
		if(fooBucket.isExists()) {
			System.out.println("Have value");
		} else {
			System.out.println("Don't have");
		}
	}

	@Test
	public void testLockKey() {
		RBucket<String> fooBucket = redissonClient.getBucket("foo");
		RLock lock = redissonClient.getLock("lock");
		lock.lock();
		fooBucket.set("bar");
		int x = 1;
		lock.unlock();
	}

}
