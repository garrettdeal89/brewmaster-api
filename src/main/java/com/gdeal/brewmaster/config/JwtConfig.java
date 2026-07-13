package com.gdeal.brewmaster.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidators;


@Configuration
public class JwtConfig {
    
    @Bean
    public SecretKey jwtSecretKey(

        @Value("${security.jwt.secret}") String encodedSecret) {

            byte[] decodedSecret = Base64.getDecoder().decode(encodedSecret);

            if (decodedSecret.length < 32) {

                throw new IllegalArgumentException(
                    "JWT secret must be at least 32 bytes fir HS256."
                );
            }

            return new SecretKeySpec(
                decodedSecret,
                "HmacSHA256"
            );
        }

    @Bean
    public JwtEncoder jwtEncoder (SecretKey jwtSecretKey) {

        return new NimbusJwtEncoder(
            new ImmutableSecret<>(jwtSecretKey)
        );
    }

    @Bean
    public JwtDecoder jwtDecoder(

        SecretKey jwtSecretKey,
        @Value("${security.jwt.issuer}")
        String issuer) {

            NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

            OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);

            jwtDecoder.setJwtValidator(validator);

            return jwtDecoder;
        }
}
