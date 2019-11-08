package com.haopn.demo;

import com.haopn.demo.bag.BagA;
import com.haopn.demo.bag.BagB;
import com.haopn.demo.bag.BagC;
import com.haopn.demo.service.AService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@AutoConfigureJdbc
@AutoConfigureTestDatabase
@TestPropertySource(properties = {
		"logging.level.ROOT=INFO",
		"logging.level.org.springframework.jdbc.core=DEBUG",
		"logging.level.org.springframework.transaction=TRACE"
})
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	AService aService;

	@Autowired
	EntityManager entityManager;

	private List<BagB> getBList(BagA a) {
		List<BagB> bList = new ArrayList<>();
		BagB b1 = new BagB("B1 object");
		b1.setBagA(a);
		BagB b2 = new BagB("B2 object");
		b2.setBagA(a);
		BagB b3 = new BagB("B3 object");
		b3.setBagA(a);

		List<BagC> cList_1 = new ArrayList<>();
		BagC c1 = new BagC();
		c1.setName("C1 object");
		c1.setBagB(b1);
		cList_1.add(c1);
		b1.setcList(cList_1);

		List<BagC> cList_2 = new ArrayList<>();
		BagC c2 = new BagC();
		c2.setName("C2 object");
		c2.setBagB(b2);
		cList_2.add(c2);
		b2.setcList(cList_2);

		bList.add(b1);
		bList.add(b2);
		bList.add(b3);
		return bList;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void initData() {
		BagA a = new BagA();
		a.setName("A object");
		a.setbList(getBList(a));
		entityManager.persist(a);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void testInsertSimple() {
		initData();
		List<BagC> cList = entityManager.createQuery(
				"select c " +
						"from BagC c " +
						"inner join fetch c.bagB b " +
						"inner join fetch b.bagA a " +
						"where a.id = :id", BagC.class)
				.setParameter("id", 1)
				.getResultList();
		Assert.assertTrue(cList.size() == 2);
	}

}
