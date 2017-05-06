package com.springBootExample.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/view/");

	}

	@Bean
	public DataSource dataSource() {
		return 
				DataSourceBuilder.create()
				.username("vaibhav")
				.password("vaibhav")
				.url("jdbc:derby:Database//ownDatabase;create=true;")
				.driverClassName("org.apache.derby.jdbc.EmbeddedDriver")
				.build();
	}

}
