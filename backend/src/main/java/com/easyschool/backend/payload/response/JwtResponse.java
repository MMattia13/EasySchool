package com.easyschool.backend.payload.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
   

    public JwtResponse(String token) {
        this.token = token;
    }
}