package com.qa.TDL.config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}



}
