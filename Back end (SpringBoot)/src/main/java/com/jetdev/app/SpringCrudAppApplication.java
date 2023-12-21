package com.jetdev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringCrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudAppApplication.class, args);
	}

}
