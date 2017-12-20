package dev.paie.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class JpaConfig {

@Bean
public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

vendorAdapter.setShowSql(true);
LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
factory.setJpaVendorAdapter(vendorAdapter);

factory.setPackagesToScan("dev.paie.entite");
factory.setDataSource(dataSource);
Properties jpaProperties = new Properties(); 

jpaProperties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");

factory.setJpaProperties(jpaProperties);
factory.afterPropertiesSet();
return factory.getObject();
}
}