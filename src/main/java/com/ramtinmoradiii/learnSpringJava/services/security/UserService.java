package com.ramtinmoradiii.learnSpringJava.services.security;

import com.ramtinmoradiii.learnSpringJava.common.utils.JWTUtil;
import com.ramtinmoradiii.learnSpringJava.dto.security.LoginDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.RegisterDTO;
import com.ramtinmoradiii.learnSpringJava.dto.security.UserDTO;
import com.ramtinmoradiii.learnSpringJava.entities.security.User;
import com.ramtinmoradiii.learnSpringJava.exceptions.NotFoundException;
import com.ramtinmoradiii.learnSpringJava.exceptions.ValidationException;
import com.ramtinmoradiii.learnSpringJava.repositories.security.PermissionRepository;
import com.ramtinmoradiii.learnSpringJava.repositories.security.RoleRepository;
import com.ramtinmoradiii.learnSpringJava.repositories.security.UserRepository;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            ModelMapper mapper,
            @Lazy PasswordEncoder passwordEncoder,
            JWTUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("کاربری با این نام کاربری یافت نشد."));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                true,
                getAuthorities(user)
        );
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

                if (role.getPermissions() != null) {
                    role.getPermissions().forEach(permission ->
                            authorities.add(new SimpleGrantedAuthority(permission.getName()))
                    );
                }
            });
        }
        return authorities;
    }

    public UserDTO register(RegisterDTO dto) {
        if (userRepository.findFirstByUsernameIgnoreCase(dto.getUsername()).isPresent()) {
            throw new ValidationException("این نام کاربری قبلا ثبت شده است.");
        }

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
            throw new ValidationException("حساب کاربری شما غیر فعال است.");
        }

        UserDTO dto = mapper.map(user, UserDTO.class);
        dto.setToken(jwtUtil.generateToken(user.getUsername()));
        return dto;
    }

    public UserDTO getUserByUserName(String username) {
        User user = userRepository.findFirstByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("کاربر یافت نشد."));
        return mapper.map(user, UserDTO.class);
    }
}