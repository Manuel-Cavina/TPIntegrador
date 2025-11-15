package com.tpi.logistica.gateway.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges

                        //Testeo    
                        .pathMatchers("/actuator/health").permitAll()

                        // Cliente: seguimiento y solicitud
                        .pathMatchers(HttpMethod.GET, "/api/v1/solicitudes/**").hasAnyRole("CLIENTE", "OPERADOR")
                        .pathMatchers(HttpMethod.POST, "/api/v1/solicitudes/**").hasRole("CLIENTE")

                        // Operador: rutas, tramos, camiones, contenedores
                        .pathMatchers("/api/v1/camiones/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/rutas/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/tramos/**").hasRole("OPERADOR")
                        .pathMatchers("/api/v1/tarifas/**").hasRole("OPERADOR")

                        // Transportista: sólo inicio/fin de tramo
                        .pathMatchers("/api/v1/tramos/inicio/**").hasRole("TRANSPORTISTA")
                        .pathMatchers("/api/v1/tramos/fin/**").hasRole("TRANSPORTISTA")

                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        return jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
            if (realmAccess != null && realmAccess.get("roles") instanceof Collection) {
                Collection<String> roles = (Collection<String>) realmAccess.get("roles");
                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            }

            return Mono.just(new JwtAuthenticationToken(jwt, authorities));
        };
    }
}
