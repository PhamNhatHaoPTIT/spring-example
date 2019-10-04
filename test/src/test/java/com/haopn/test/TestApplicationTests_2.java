package com.haopn.test;

import com.haopn.test.model.Todo;
import com.haopn.test.repository.TodoRepository;
import com.haopn.test.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class TestApplicationTests_2 {

    @MockBean
    private TodoRepository mockTodoRepo;

    @TestConfiguration
    public static class TodoServiceTestConfig {
        @Bean
        public TodoService todoService() {
            return new TodoService();
        }
    }

    @Autowired
    private TodoService todoService;

    @Test
    public void isExistTodo() {
        List<Todo> todoList = todoService.getAll();
        Assert.assertThat(todoList.size(), Matchers.notNullValue());
    }

}
