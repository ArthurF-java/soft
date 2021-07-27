package com.example.soft.controllers;


import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.service.ProductDescriptionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descriptions")
@AllArgsConstructor
public class ProductDescriptionController {

    ProductDescriptionService descriptionService;

    @GetMapping("/{description_id}")
    public ProductDescriptionDto findProductDescriptionById(@PathVariable long description_id){
        return descriptionService.findProductDescriptionById(description_id);
    }

    @GetMapping("/order_id/{order_id}")
    public List<ProductDescriptionDto> findAllProductDescriptionByOrderId(
            @PathVariable long order_id){
        return descriptionService.findAllProductDescriptionByOrderId(order_id);
    }

    @PostMapping
    public ProductDescriptionDto addProductDescription
            (@RequestBody ProductDescriptionDto descriptionDto){
        return descriptionService.addProductDescription(descriptionDto);
    }

    @PutMapping
    public ProductDescriptionDto editProductDescription
            (@RequestBody ProductDescriptionDto descriptionDto){
        return descriptionService.editProductDescription(descriptionDto);
    }

    @DeleteMapping("/{description_id}")
    public String deleteProductDescription
            (@PathVariable long description_id){
        return descriptionService.deleteProductDescription(description_id);
    }

}
