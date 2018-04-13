package configurations;

import models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setHibernateProperties(properties());
        factoryBean.setPackagesToScan("models");
        factoryBean.setDataSource(getDataSource());
        try {
            factoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    private Properties properties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.property.dialect"));
        properties.put("hibernate.connection.autocommit", env.getProperty("hibernate.property.connection.autocommit"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.property.format_sql"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.property.show_sql"));
        properties.put("hibernate.default_schema", env.getProperty("hibernate.property.default_schema"));
        return properties;
    }
}
