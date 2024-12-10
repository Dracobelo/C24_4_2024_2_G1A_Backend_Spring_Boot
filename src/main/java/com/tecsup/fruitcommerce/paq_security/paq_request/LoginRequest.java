package com.tecsup.fruitcommerce.paq_security.paq_request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}