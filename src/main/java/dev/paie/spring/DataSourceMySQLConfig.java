package dev.paie.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceMySQLConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");// Todo mariaDB
		dataSource.setUrl("jdbc:mariadb://localhost:3306/sirh-paie?useSSL=false");
		dataSource.setUsername("sirh");
		dataSource.setPassword("sirh");
		return dataSource;
	}
}