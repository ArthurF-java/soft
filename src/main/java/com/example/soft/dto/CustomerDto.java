package com.example.soft.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String street;
    private int house;
    private int flat;

}
