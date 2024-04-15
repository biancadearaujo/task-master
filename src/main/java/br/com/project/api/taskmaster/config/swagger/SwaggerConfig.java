package br.com.project.api.taskmaster.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//@Configuration
public class SwaggerConfig {

	/*@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("Api Task Master")
						.description("your api description")
						.version("1.0"));
	}*/
	/*
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().description("Description")
				.title("title")
				.version("V1");
		return new OpenAPI().info(info);
	}*/
}
