package com.gdeal.brewmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

                // Temporary Sprint 8.1 rule.
                // Authentication rules will be tightened after JWT is added.
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().permitAll()
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
        
}
