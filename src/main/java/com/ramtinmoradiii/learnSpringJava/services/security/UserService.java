package com.ramtinmoradiii.learnSpringJava.services.security;

import com.ramtinmoradiii.learnSpringJava.dto.security.LoginDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.RegisterDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.UserDTO;
import com.ramtinmoradiii.learnSpringJava.entities.security.User;
import com.ramtinmoradiii.learnSpringJava.exceptions.NotFoundException;
import com.ramtinmoradiii.learnSpringJava.exceptions.ValidationException;
import com.ramtinmoradiii.learnSpringJava.repositories.security.PermissionRepository;
import com.ramtinmoradiii.learnSpringJava.repositories.security.RoleRepository;
import com.ramtinmoradiii.learnSpringJava.repositories.security.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PermissionRepository permissionRepository,
                       ModelMapper mapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO register(RegisterDTO dto) {
        User user = mapper.map(dto, User.class);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public UserDTO login(LoginDTO loginDTO) {
        User user = userRepository.findFirstByUsernameIgnoreCase(loginDTO.getUsername())
                .orElseThrow(() -> new NotFoundException("کاربری با این نام کاربری یافت نشد."));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ValidationException("نام کاربری یا رمز عبور اشتباه است.");
        }

        if (!user.getEnable()) {
            throw new ValidationException("کاربری شما غیر فعال شده است. لطفا با پشتیبانی تماس حاصل فرمایید.");
        }

        return mapper.map(user, UserDTO.class);
    }
}