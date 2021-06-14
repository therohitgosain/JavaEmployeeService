package com.allianz.exercise.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    /**
     * Product api.
     *
     * @return the docket
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.allianz.exercise.poc.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());

    }
    
    /**
     * Meta data.
     *
     * @return the api info
     */
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "POC Application",
                "POC Application for Employee Data",
                "1.0",
                "Terms of service",
                new Contact("Rohit Gosain", "https://Allianz.com", "rohit.gosain@allianz.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}