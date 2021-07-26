package com.example.soft.dto;

import com.example.soft.entity.ProductDescription;
import com.example.soft.entity.enumeracion.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private long id;
    private long employeeId;
    private long customerId;
    private boolean isNeedInstallation;
    private ProductType productType;
    private int amount;
    private LocalDate orderTime;
    private LocalDate executionTime;
    private LocalDate installTime;
    private List<ProductDescription> descriptionList;
}
