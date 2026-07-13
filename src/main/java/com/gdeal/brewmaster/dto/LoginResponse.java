package com.gdeal.brewmaster.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "JWT authentication response."
)
public class LoginResponse {
    
    private String accessToken;
    private String tokenType;
    private long expiresIn;

    public LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn) {

            this.accessToken = accessToken;
            this.tokenType = tokenType;
            this.expiresIn = expiresIn;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public long getExpiresIn() {
            return expiresIn;
        }
}
