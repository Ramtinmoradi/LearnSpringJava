package com.ramtinmoradiii.learnSpringJava.dto.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String mobile;
    private Boolean enable = true;
    private LocalDateTime registerDate;
}
