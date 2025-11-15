package com.tpi.logistica.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${logistica-gateway.url-camiones}") String camionesUri,
                                        @Value("${logistica-gateway.url-contenedores}") String contenedoresUri,
                                        @Value("${logistica-gateway.url-solicitudes}") String solicitudesUri,
                                        @Value("${logistica-gateway.url-tarifas}") String tarifasUri,
                                        @Value("${logistica-gateway.url-rutas}") String rutasUri,
                                        @Value("${logistica-gateway.url-usuarios}") String usuariosUri,
                                        @Value("${logistica-gateway.url-maps}") String mapsUri,
                                        @Value("${internal.token}") String internalToken) {

        return builder.routes()
                .route(p -> p.path("/api/v1/camiones/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(camionesUri))

                .route(p -> p.path("/api/v1/contenedores/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(contenedoresUri))

                .route(p -> p.path("/api/v1/solicitudes/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(solicitudesUri))

                .route(p -> p.path("/api/v1/tarifas/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(tarifasUri))

                .route(p -> p.path("/api/v1/rutas/**", "/api/v1/tramos/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(rutasUri))

                .route(p -> p.path("/api/v1/usuarios/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(usuariosUri))

                .route(p -> p.path("/api/v1/maps/**")
                        .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                        .uri(mapsUri))
                .build();
    }

}
