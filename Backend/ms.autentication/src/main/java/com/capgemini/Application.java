package com.capgemini;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		System.out.println("Servicio de autenticación de proyecto pizzeria funcionando y preparado.");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	  @Bean
	  DataSource dataSource(Environment env) {
	  	DriverManagerDataSource ds = new DriverManagerDataSource();
	  	ds.setDriverClassName("com.mysql.jdbc.Driver");
	  	ds.setUrl("jdbc:mysql://localhost:3306/usuarios_pizzeria");
	  	ds.setUsername("root");
	  	ds.setPassword("root");
	  	return ds;
	  }	*/

}
