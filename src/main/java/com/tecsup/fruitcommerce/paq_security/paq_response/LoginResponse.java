package com.tecsup.fruitcommerce.paq_security.paq_response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;
    private boolean hasRole; // Nuevo campo para indicar si el usuario tiene roles asignados

    // Constructor con cuatro parámetros (incluye hasRole)
    public LoginResponse(String username, List<String> roles, String jwtToken, boolean hasRole) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
        this.hasRole = hasRole;
    }

    // Constructor con tres parámetros (opcional, para mantener compatibilidad)
    public LoginResponse(String username, List<String> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }
}
