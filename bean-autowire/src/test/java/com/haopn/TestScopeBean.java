package com.haopn;


import com.haopn.config.AppConfig;
import com.haopn.model.PrototypeBean;
import com.haopn.model.SingletonBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class TestScopeBean {

    @Test
    public void givenPrototypeInjection_WhenObjectFactory_ThenNewInstanceReturn() {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonBean firstContext = context.getBean(SingletonBean.class);
        SingletonBean secondContext = context.getBean(SingletonBean.class);

        PrototypeBean firstInstance = firstContext.getPrototypeBean();
        PrototypeBean secondInstance = secondContext.getPrototypeBean();

        Assert.assertTrue("New instance expected", firstInstance != secondInstance);
    }

}
