package com.easyschool.backend.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String surname;
    private String birthdate;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String name, String surname, String birthdate, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.roles = roles;
    }
}
