package com.example.soft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescriptionDto {
    private Long id;
    private Long orderId;
    private int width;
    private int height;
    private String color;
    private String comments;
}
