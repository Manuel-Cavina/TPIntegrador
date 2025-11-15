package com.tpi.logistica.servicio_usuarios.config;

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
                        .title("Servicio usuarios API")
                        .description("Microservicio de gestión de usuarios del sistema logístico")
                        .version("1.0.0")
        );
    }
}
