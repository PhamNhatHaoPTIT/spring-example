package com.haopn.transactional;

import com.haopn.transactional.service.MovieService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTest {

    @Autowired
    MovieService movieService;
    @Test
    public void testLoadMovie() {
        Assert.assertTrue(movieService.getMovie(1) != null);
    }

}
