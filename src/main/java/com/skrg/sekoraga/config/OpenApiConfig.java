package com.skrg.sekoraga.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sekoraga API")
                        .description(
                                "API documentation for Sekoraga, a platform for managing educational institutions.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@sekoraga.co.id")
                                .url("https://sekoraga.co.id"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .openapi("3.0.3")
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return openApi -> openApi.getPaths().values()
                .forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                    if (operation.getParameters() != null) {
                        operation.getParameters().forEach(parameter -> {
                            if (parameter.getName().equals("criteria") || parameter.getName().equals("pageable")) {
                                parameter.setRequired(false);
                            }
                        });
                    }
                }));
    }
}
