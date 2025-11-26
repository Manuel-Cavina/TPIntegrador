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
                        @Value("${logisticaGateway.urlCamiones}") String camionesUri,
                        @Value("${logisticaGateway.urlContenedores}") String contenedoresUri,
                        @Value("${logisticaGateway.urlSolicitudes}") String solicitudesUri,
                        @Value("${logisticaGateway.urlTarifas}") String tarifasUri,
                        @Value("${logisticaGateway.urlRutas}") String rutasUri,
                        @Value("${logisticaGateway.urlUsuarios}") String usuariosUri,
                        @Value("${logisticaGateway.urlMaps}") String mapsUri,
                        @Value("${internal.token}") String internalToken) {

                return builder.routes()
                                .route(p -> p.path("/api/v1/camiones/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(camionesUri))

                                .route(p -> p.path("/api/v1/contenedores/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(contenedoresUri))

                                .route(p -> p.path("/api/v1/solicitudes/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(solicitudesUri))

                                .route(p -> p.path("/api/v1/tarifas/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(tarifasUri))

                                .route(p -> p.path("/api/v1/rutas/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(rutasUri))
                                .route(p -> p.path("/api/v1/tramos/**")
                                                .filters(f -> f.stripPrefix(2).addRequestHeader("X-Internal-Token",
                                                                internalToken))
                                                .uri(rutasUri))

                                .route(p -> p.path("/api/v1/usuarios/**")
                                                .filters(f -> f.addRequestHeader("X-Internal-Token", internalToken))
                                                .uri(usuariosUri))

                                .route(p -> p.path("/api/v1/maps/**")
                                                .filters(f -> f.rewritePath("/api/v1/maps/(?<segment>.*)",
                                                                "/geo/${segment}")
                                                                .addRequestHeader("X-Internal-Token", internalToken))
                                                .uri(mapsUri))
                                .build();
        }

}
