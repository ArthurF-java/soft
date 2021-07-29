package com.example.soft.dto;

import com.example.soft.entity.enumeracion.ProductType;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private UserDto employee;
    private CustomerDto customer;
    private Boolean isNeedInstallation;
    private ProductType productType;
    private Integer amount;
    private LocalDate orderTime;
    private LocalDate executionTime;
    private LocalDate installTime;
    private List<ProductDescriptionDto> descriptionList;
}
