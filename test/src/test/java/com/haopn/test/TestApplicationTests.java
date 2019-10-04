package com.haopn.test;

import com.haopn.test.model.Todo;
import com.haopn.test.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {


	@Autowired
	private TodoService todoService;

	@Test
	public void isExistTodo() {
		List<Todo> todoList = todoService.getAll();
		assertThat(todoList, Matchers.notNullValue());
	}

}
