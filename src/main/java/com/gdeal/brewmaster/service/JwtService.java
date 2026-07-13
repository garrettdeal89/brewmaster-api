package com.gdeal.brewmaster.service;

import com.gdeal.brewmaster.model.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {
    
    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final long expirationSeconds;

    public JwtService (

        JwtEncoder jwtEncoder,
        @Value("${security.jwt.issuer}")
        String issuer,
        @Value("${security.jwt.expiration-seconds}")
        long expirationSeconds) {

            this.jwtEncoder = jwtEncoder;
            this.issuer = issuer;
            this.expirationSeconds = expirationSeconds;
        }

        public String generateToken(UserAccount userAccount) {

            Instant issuedAt = Instant.now();
            Instant expiresAt = issuedAt.plusSeconds(expirationSeconds);

        JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer(issuer)
        .issuedAt(issuedAt)
        .expiresAt(expiresAt)
        .subject(userAccount.getUsername())
        .claim("userId", userAccount.getId())
        .claim("role", userAccount.getRole())
        .build();

        JwsHeader header = JwsHeader
        .with(MacAlgorithm.HS256)
        .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(header, claims);

        return jwtEncoder
        .encode(parameters)
        .getTokenValue();
        }

        public long getExpirationSeconds() {

            return expirationSeconds;
        }
}
