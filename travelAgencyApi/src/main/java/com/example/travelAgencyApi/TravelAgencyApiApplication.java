package com.example.travelAgencyApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelAgencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApiApplication.class, args);
	}

	//After loging in through Spring security,if it gets you to an error page, try introducing the url for swagger-ui again, here it is: http://localhost:8080/doc/swagger-ui/index.html
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("TravelAgencyApplication")
				.version("beta")
				.description("This is an API for a technical test for the Hack a Boss Java/Springboot bootcamp."));
	}

}
