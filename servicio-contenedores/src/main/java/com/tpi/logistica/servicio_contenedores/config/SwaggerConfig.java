package com.tpi.logistica.servicio_contenedores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(
                new Info()
                        .title("Servicio Contenedores API")
                        .description("Microservicio de gestión de contenedores del sistema logístico")
                        .version("1.0.0")
        );
    }
}
