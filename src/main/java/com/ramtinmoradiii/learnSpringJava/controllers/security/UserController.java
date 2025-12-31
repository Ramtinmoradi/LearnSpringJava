package com.ramtinmoradiii.learnSpringJava.controllers.security;

import com.ramtinmoradiii.learnSpringJava.common.ApiResponse;
import com.ramtinmoradiii.learnSpringJava.dto.security.LoginDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.RegisterDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.UserDTO;
import com.ramtinmoradiii.learnSpringJava.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ApiResponse<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        return ApiResponse.success(service.login(loginDTO));
    }

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ApiResponse.success(service.register(registerDTO));
    }
}
