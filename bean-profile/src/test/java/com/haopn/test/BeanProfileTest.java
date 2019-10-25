package com.haopn.test;

import com.haopn.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class BeanProfileTest {

    @Autowired
    private String devBean;

    @Test
    public void isExistDevBean() {
        Assert.assertTrue(devBean.equals("This is bean in dev"));
        Assert.assertNotNull(devBean);
    }

}
