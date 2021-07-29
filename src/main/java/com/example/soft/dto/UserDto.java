package com.example.soft.dto;

import com.example.soft.entity.enumeracion.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String email;
    private Role role;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
}
