package com.tpi.logistica.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges

                        .pathMatchers("/actuator/health").permitAll()

                        .pathMatchers(HttpMethod.GET, "/api/v1/solicitudes/**").hasAnyRole("CLIENTE", "OPERADOR")
                        .pathMatchers(HttpMethod.POST, "/api/v1/solicitudes/**").hasRole("CLIENTE")

                        .pathMatchers("/api/v1/camiones/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/rutas/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/tramos/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/tarifas/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/contenedores/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/usuarios/**").hasRole("OPERADOR")

                        .pathMatchers("/api/v1/tramos/inicio/**").hasRole("TRANSPORTISTA")
                        .pathMatchers("/api/v1/tramos/fin/**").hasRole("TRANSPORTISTA")

                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}