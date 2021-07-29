package com.example.soft.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
    private List<OrderDto> orderList;
}
