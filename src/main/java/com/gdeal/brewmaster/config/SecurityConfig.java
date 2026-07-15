package com.gdeal.brewmaster.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        //
        @Bean
        public SecurityFilterChain securityFilterChain (

        HttpSecurity http) throws Exception {

        http
                // REST APIs using JWT do not use browser-based CSRF tokens.
                .csrf(csrf -> csrf.disable())

                // JWT authentication will be stateless.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                )
        )

                // Authentication rules will be tightened after JWT is added.
                .authorizeHttpRequests(authorize -> authorize

                        //Authentication
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login"
                        ).permitAll()

                        //public health endpoimt
                        .requestMatchers(
                                "/api/health"
                        ).permitAll()

                        //OpenAPI
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "v3/api-docs/**"
                        ).permitAll()

                        //public read operations
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/recipes/**",
                                "/api/ingredients/**",
                                "/api/brew-methods/**",
                                "/api/shared-recipes/**"
                        ).permitAll()

                        //All other API operations require authentication
                        .requestMatchers(
                                "/api/**"
                        ).authenticated()

                        .anyRequest().permitAll()
        )
                .oauth2ResourceServer(oauth2 ->

                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter()
                                )
                        )
                )

                // Disable Spring Security's generated login mechanisms.
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
        
        @Bean
        public JwtAuthenticationConverter jwtAuthenticationConverter() {

                JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

                converter.setJwtGrantedAuthoritiesConverter(jwt -> {

                        String role = jwt.getClaimAsString("role");

                        if (role == null || role.isBlank()) {
                                
                                return List.of();
                        }

                        return List.of(

                                new SimpleGrantedAuthority(
                                        "ROLE_" + role
                                )
                        );
                });

                return converter;
        }
}
