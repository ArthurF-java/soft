package com.example.soft.service;

import com.example.soft.dto.ProductDescriptionDto;

import java.util.List;

public interface ProductDescriptionService {

    ProductDescriptionDto findProductDescriptionById (long descriptionId);
    ProductDescriptionDto addProductDescription(ProductDescriptionDto descriptionDto);
    ProductDescriptionDto editProductDescription(ProductDescriptionDto descriptionDto);
    String deleteProductDescription(long descriptionDtoId);
    List<ProductDescriptionDto> findAllProductDescriptionByOrderId(long orderId);


}
