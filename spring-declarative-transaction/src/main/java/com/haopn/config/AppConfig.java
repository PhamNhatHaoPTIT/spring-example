package com.haopn.config;

import com.haopn.jdbcTemplate.StudentJDBCTemplate;
import com.mysql.cj.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean(name = "jdbcTemplateObject")
    public JdbcTemplate jdbcTemplate() throws Exception {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws Exception {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriver(new Driver());
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/jdbcTemplate?noAccessToProcedureBodies=true");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root1234");
        return basicDataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

}
