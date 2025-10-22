package com.claim.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

/**
 * Configuration class for authorization server settings using HMAC for JWT signing.
 * This matches the JWT creation and validation approach used in controllers and filters.
 */
@Configuration
public class AuthServerConfig {

    /**
     * Creates a shared secret key for JWT operations.
     * This must match the key used in controllers and filters.
     * @return SecretKey for HMAC operations
     */
    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor("secretsecretsecretsecretsecretsecretsecretsecret".getBytes());
    }

    /**
     * JWT decoder for validating JWT tokens using HMAC.
     * @param secretKey the secret key for validation
     * @return JwtDecoder instance
     */
    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey) {
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
