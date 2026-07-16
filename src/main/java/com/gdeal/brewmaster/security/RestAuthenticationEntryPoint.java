package com.gdeal.brewmaster.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdeal.brewmaster.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    public final ObjectMapper objectMapper;

    public RestAuthenticationEntryPoint(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(

        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException)

        throws IOException {

            ApiError error = new ApiError(

                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                "Authentication is required to access this resource."
            );

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            objectMapper.writeValue(

                response.getOutputStream(),
                error
            );
        }
}
