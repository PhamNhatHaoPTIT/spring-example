package com.haopn.demo;

import com.haopn.demo.entity.NewPerson;
import com.haopn.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
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
	public void testAddNewField() {
		// GIVEN: person object with 2 fields: id and abc
		RBucket<Person> personRBucket = redissonClient.getBucket("person_2");
		Person person = null;
		// WHEN: add new field: xyz
		// 1. delete old bucket
		// 2. add new bucket with new field
		if(personRBucket.isExists()) {
			person = personRBucket.get();
			personRBucket.delete();
			RBucket<NewPerson> newPersonRBucket = redissonClient.getBucket("person_2");
			NewPerson newPerson = new NewPerson();
			newPerson.setId(person.getId());
			newPerson.setAbc(person.getAbc());
			newPerson.setXyz("xyz");
			newPersonRBucket.set(newPerson);
		}
		// THEN:
	}

	@Test
	public void testDeleteField() {
		// GIVEN: person object with fields: id, abc, xyz
		RBucket<Person> personRBucket = redissonClient.getBucket("person_1");
		// WHEN: delete field xyz in Java object
		Person person = null;
		if(personRBucket.isExists()) {
			person = personRBucket.get();
			System.out.println(person.toString());
			personRBucket.set(person);
		}
		// THEN: xyz will be deleted in bucket serialization
	}

	@Test
	public void testRenameField() {
		// GIVEN: person object with 2 fields: id and abc

		// WHEN: rename field abc -> xyz

		// THEN: auto map new name of field sequence
	}

}
