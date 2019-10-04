package com.bean.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyApplicationTests {

	/*@Test
	public void isExistDispatcherServlet() {
		ApplicationContext ctx = SpringApplication.run(VerifyApplication.class);
		//DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		//assertTrue(dispatcherServlet != null);
		assertNotNull(ctx.getBean("dispatcherServlet"));
	}*/

	@Test
	public void isExistDispatcherServlet() {
		ApplicationContext ctx = SpringApplication.run(VerifyApplication.class);
		//DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		//assertTrue(dispatcherServlet != null);
		assertNotNull(ctx.getBean("dispatcherServlet"));
	}

}
