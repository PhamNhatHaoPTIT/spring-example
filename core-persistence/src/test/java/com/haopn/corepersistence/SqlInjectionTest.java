package com.haopn.corepersistence;

import com.haopn.corepersistence.controller.StudentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class SqlInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInjectStatement() throws Exception {
        String id = "23 or 1=1";
        mockMvc.perform(get("/student/statement").param("id", id)).
                andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(
                            (jsonPath("$.*", hasSize(greaterThan(0))))
                         );
    }

    @Test
    public void testInjectPrepareStatement() throws Exception {
        String id = "23 or 1=1";
        mockMvc.perform(get("/student/prepare_statement").param("id", id)).
                andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(
                        (jsonPath("$.*", hasSize(equalTo(0))))
                );
    }

}
