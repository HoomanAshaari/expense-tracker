package com.ashaari.hooman.expensetracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${application.title}")
    private String title;
    @Value("${application.description}")
    private String description;
    @Value("${application.version}")
    private String version;

    @Bean
    public Info info() {
        return new Info()
                .title(title)
                .description(description)
                .version(version);
    }

    @Bean
    public OpenAPI groupedOpenApi(Info info) {
        return new OpenAPI().info(info);
    }

}
