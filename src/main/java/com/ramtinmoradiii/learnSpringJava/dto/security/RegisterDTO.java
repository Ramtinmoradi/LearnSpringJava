package com.ramtinmoradiii.learnSpringJava.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String mobile;
}
