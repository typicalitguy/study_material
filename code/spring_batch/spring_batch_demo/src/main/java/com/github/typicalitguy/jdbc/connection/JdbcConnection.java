package com.github.typicalitguy.jdbc.connection;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JdbcConnection {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.university.datasource")
	public DataSource universitydatasource() {
		return DataSourceBuilder.create().build();
	}
}
