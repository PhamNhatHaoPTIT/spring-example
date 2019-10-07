package com.haopn.test;

import com.haopn.test.model.BeanPrototype;
import com.haopn.test.model.BeanSingleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

@RunWith(SpringRunner.class)
public class BeanScopeTest {

    @TestConfiguration
    public static class BeanConfig {
        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public BeanPrototype beanPrototype() {
            return new BeanPrototype();
        }
        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
        public BeanSingleton beanSingleton() {
            return new BeanSingleton();
        }
    }

    @Test
    public void beanPrototypeTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        BeanPrototype bean1 = (BeanPrototype) applicationContext.getBean("beanPrototype");
        BeanPrototype bean2 = (BeanPrototype) applicationContext.getBean("beanPrototype");
        assertThat(bean1, not(sameInstance(bean2)));
    }

    @Test
    public void beanSingletonTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        BeanSingleton bean1 = (BeanSingleton) applicationContext.getBean("beanSingleton");
        BeanSingleton bean2 = (BeanSingleton) applicationContext.getBean("beanSingleton");
        assertThat(bean1, (sameInstance(bean2)));
    }

}
