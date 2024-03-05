package com.k35dl.g6.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    
    public AuthResponse(String token, String message) {
        super();
        this.token = token;
        this.message = message;
    }
}
