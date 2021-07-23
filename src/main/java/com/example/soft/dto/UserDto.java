package com.example.soft.dto;

import com.example.soft.entity.enumeracion.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String email;
    private Role role;
    private String city;
    private String street;
    private int house;
    private int flat;
}
