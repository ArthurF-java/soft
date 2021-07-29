package com.example.soft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDescriptionDto {
    private Long id;
    private Long orderId;
    private Integer width;
    private Integer height;
    private String color;
    private String comments;
}
