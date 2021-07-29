package com.example.soft.dto.assembler;

import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.ProductDescriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDescriptionAssembler {

    public ProductDescriptionEntity assemble(final ProductDescriptionDto descriptionDto, OrderEntity order) {
        ProductDescriptionEntity description = null;
        if (descriptionDto != null) {
            description = new ProductDescriptionEntity();
            description.setId(descriptionDto.getId());
            description.setOrder(descriptionDto.getOrderId().equals(order.getId()) ?
                    order : null);
            description.setWidth(descriptionDto.getWidth());
            description.setHeight(descriptionDto.getHeight());
            description.setColor(descriptionDto.getColor());
            description.setComments(descriptionDto.getComments());
        }
        return description;
    }

    public ProductDescriptionDto disassemble(final ProductDescriptionEntity description) {
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
}
