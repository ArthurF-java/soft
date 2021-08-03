package com.example.soft.controllers;


import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.service.ProductDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/descriptions")
@RequiredArgsConstructor
public class ProductDescriptionController {

    private final ProductDescriptionService descriptionService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public ProductDescriptionDto findProductDescriptionById(@PathVariable final long id) {
        return descriptionService.findProductDescriptionById(id);
    }

    @GetMapping("/order_id/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public List<ProductDescriptionDto> findAllProductDescriptionByOrderId(@PathVariable final long id) {
        return descriptionService.findAllProductDescriptionByOrderId(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public ProductDescriptionDto createProductDescription(@RequestBody final ProductDescriptionDto descriptionDto) {
        return descriptionService.createProductDescription(descriptionDto);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public ProductDescriptionDto updateProductDescription(@RequestBody final ProductDescriptionDto descriptionDto) {
        return descriptionService.updateProductDescription(descriptionDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SALES')")
    public void deleteProductDescription(@PathVariable final long id) {
        descriptionService.deleteProductDescription(id);
    }
}
