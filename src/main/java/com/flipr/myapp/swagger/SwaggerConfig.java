package com.flipr.myapp.swagger;


import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex(".*"), regex(".*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Flipr API")
				.description("ECommerce Application For Flipr")
				.termsOfServiceUrl("http://localhost:8082/")
				.contact("praneethvaleti1@gmail.com").license("Praneeth Valeti")
				.licenseUrl("praneethvaleti1@gmail.com").version("1.0").build();
	}
}
