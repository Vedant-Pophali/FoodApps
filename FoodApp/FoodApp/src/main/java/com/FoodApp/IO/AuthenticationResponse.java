package com.FoodApp.IO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    @Email
    private String email;
    private String token;
}