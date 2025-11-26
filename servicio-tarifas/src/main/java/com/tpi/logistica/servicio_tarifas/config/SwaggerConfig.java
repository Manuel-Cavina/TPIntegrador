package com.tpi.logistica.servicio_tarifas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Servicio Tarifas API")
                        .description("API del Microservicio de Tarifas")
                        .version("1.0.0")
        );
    }
}
