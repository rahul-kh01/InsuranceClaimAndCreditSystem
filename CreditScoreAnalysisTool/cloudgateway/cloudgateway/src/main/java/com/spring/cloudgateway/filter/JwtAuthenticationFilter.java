package com.spring.cloudgateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

/**
 * JWT Authentication Filter for Spring Cloud Gateway
 * Validates JWT tokens and adds user information to request headers
 */
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // Skip JWT validation for login and register endpoints
            String path = request.getURI().getPath();
            if (path.contains("/login") || path.contains("/register")) {
                return chain.filter(exchange);
            }

            String token = getTokenFromRequest(request);
            
            if (!StringUtils.hasText(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            try {
                // Validate JWT token
                String secret = System.getenv("JWT_SECRET");
                if (secret == null || secret.isEmpty()) {
                    secret = "default-secret-key-for-development-only-change-in-production";
                }
                SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
                Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

                String username = claims.getSubject();
                
                // Add user information to request headers for downstream services
                ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Name", username)
                    .header("X-User-Subject", claims.getSubject())
                    .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());
                
            } catch (Exception e) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        };
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static class Config {
        // Configuration properties if needed
    }
}
