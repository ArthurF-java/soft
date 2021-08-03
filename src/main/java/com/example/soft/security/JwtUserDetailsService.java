package com.example.soft.security;

import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import com.example.soft.security.jwt.JwtUser;
import com.example.soft.security.jwt.JwtUserFactory;
import com.example.soft.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.soft.entity.enumeracion.Role.ROLE_SWAGGER;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${hardcode.username}")
    private String username;
    @Value("${hardcode.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByPhone(username);
        UserEntity userHardCode = new UserEntity();
        userHardCode.setPhone(username);
        userHardCode.setPassword(passwordEncoder.encode(password));
        userHardCode.setRole(ROLE_SWAGGER);
        if (user == null) {
            if (username.equals(userHardCode.getPhone())) {
                return JwtUserFactory.create(userHardCode);
            } else {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
