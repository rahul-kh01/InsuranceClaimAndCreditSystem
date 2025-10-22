package com.spring.cloudgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecretKey jwtSecretKey() {
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.isEmpty()) {
            secret = "default-secret-key-for-development-only-change-in-production";
        }
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(SecretKey secretKey) {
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/users/login", "/users/register").permitAll()
                .pathMatchers("/users/**").authenticated()
                .pathMatchers("/score/**").authenticated()  // Fixed path to match credit service endpoints
                .pathMatchers("/claims/**").authenticated()
                .anyExchange().permitAll())
            .csrf(csrf -> csrf.disable())
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtDecoder(jwtDecoder(jwtSecretKey()))));
        return http.build();
    }
}
