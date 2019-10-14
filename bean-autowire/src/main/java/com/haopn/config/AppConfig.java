package com.haopn.config;

import com.haopn.model.PrototypeBean;
import com.haopn.model.SingletonBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.haopn.model")
public class AppConfig {

}
