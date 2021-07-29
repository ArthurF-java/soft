package com.example.soft.security.jwt;

import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getPhone(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                true
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List <Role>  userRoles = new ArrayList<>();
        userRoles.add(role);
        return userRoles.stream()
                .map(role1 ->
                        new SimpleGrantedAuthority(role1.name())
                ).collect(Collectors.toList());
    }
}
