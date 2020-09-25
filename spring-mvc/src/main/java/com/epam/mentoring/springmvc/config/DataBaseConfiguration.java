package com.epam.mentoring.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Kaikenov Adilhan
**/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.epam.mentoring.springmvc.repository"})
@PropertySource("classpath:mysql.properties")
@ComponentScan("com.epam.mentoring.springmvc")
public class DataBaseConfiguration {

    private static final String DATASOURCE_DRIVER_CLASS_NAME = "datasource.driver-class-name";
    private static final String DATASOURCE_URL = "datasource.url";
    private static final String DATASOURCE_USERNAME = "datasource.username";
    private static final String DATASOURCE_PASSWORD = "datasource.password";

    private static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    private static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    private static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    private static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DATASOURCE_DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(DATASOURCE_URL));
        dataSource.setUsername(env.getProperty(DATASOURCE_USERNAME));
        dataSource.setPassword(env.getProperty(DATASOURCE_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setPackagesToScan("com.epam.mentoring.springmvc.entity");

        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        lef.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        lef.setJpaProperties(hibernateProperties());

        lef.setPersistenceUnitName("mySqlPU");

        return lef;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.setProperty(HIBERNATE_CONNECTION_DRIVER_CLASS,
                env.getRequiredProperty(HIBERNATE_CONNECTION_DRIVER_CLASS));
        properties.setProperty(HIBERNATE_CONNECTION_URL,
                env.getRequiredProperty(HIBERNATE_CONNECTION_URL));
        properties.setProperty(HIBERNATE_CONNECTION_USERNAME,
                env.getRequiredProperty(HIBERNATE_CONNECTION_USERNAME));
        properties.setProperty(HIBERNATE_CONNECTION_PASSWORD,
                env.getRequiredProperty(HIBERNATE_CONNECTION_PASSWORD));
        properties.setProperty(HIBERNATE_DIALECT,
                env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.setProperty(HIBERNATE_SHOW_SQL,
                env.getRequiredProperty(HIBERNATE_SHOW_SQL));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(final DataSource dataSource) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
