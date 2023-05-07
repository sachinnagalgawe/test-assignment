package com.example.userimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.userimport.repository")
@ComponentScan(basePackages = { "com.example.userimport.*" })
@EntityScan("com.example.userimport.entity") 
@SpringBootApplication
public class UserimportApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserimportApplication.class, args);
	}

}
