package com.example.soft.dto.assembler;

import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.entity.ProductDescription;
import com.example.soft.exeption_handing.orders.OrderNotFoundException;
import com.example.soft.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDescriptionAssembler {

    OrderRepository orderRepository;

    public ProductDescriptionDto assemblerFromEntityToDto(ProductDescription description) {
        ProductDescriptionDto descriptionDto = null;
        if (description != null) {
            descriptionDto = new ProductDescriptionDto();
            descriptionDto.setId(description.getId());
            descriptionDto.setOrderId(description.getOrder().getId());
            descriptionDto.setWidth(description.getWidth());
            descriptionDto.setHeight(description.getHeight());
            descriptionDto.setColor(description.getColor());
            descriptionDto.setComments(description.getComments());
        }
        return descriptionDto;
    }

    public ProductDescription assemblerFromDtoToEntity(ProductDescriptionDto descriptionDto) {
        ProductDescription description = null;
        if (descriptionDto != null) {
            description = new ProductDescription();
            description.setId(descriptionDto.getId());
            description.setOrder(orderRepository.getById(descriptionDto.getOrderId()));
            description.setWidth(descriptionDto.getWidth());
            description.setHeight(descriptionDto.getHeight());
            description.setColor(descriptionDto.getColor());
            description.setComments(descriptionDto.getComments());
        }
        return description;
    }


}
