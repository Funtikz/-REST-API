package org.example.restapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                  new Info().title("Rest-api")
                );
    }
}
