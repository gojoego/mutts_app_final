package com.mutts_app;

import com.mutts_app.repositories.pojos.Messages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class MySQL_MVC {

	public static void main(String[] args) {

		SpringApplication.run(MySQL_MVC.class, args);



	}
}
