package com.example.soft.service;

import com.example.soft.dto.ProductDescriptionDto;

import java.util.List;

public interface ProductDescriptionService {

    ProductDescriptionDto findProductDescriptionById (long descriptionId);

    ProductDescriptionDto createProductDescription(ProductDescriptionDto descriptionDto);

    ProductDescriptionDto updateProductDescription(ProductDescriptionDto descriptionDto);

    void deleteProductDescription(long descriptionDtoId);

    List<ProductDescriptionDto> findAllProductDescriptionByOrderId(long orderId);


}
