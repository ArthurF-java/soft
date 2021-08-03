package com.example.soft.dto;

import com.example.soft.entity.enumeracion.Role;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
}
