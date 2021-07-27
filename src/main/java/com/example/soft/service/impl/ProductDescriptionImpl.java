package com.example.soft.service.impl;

import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.dto.assembler.ProductDescriptionAssembler;
import com.example.soft.entity.ProductDescriptionEntity;
import com.example.soft.exeption_handing.orders.OrderNotFoundException;
import com.example.soft.exeption_handing.users.ProductDescriptionNotFound;
import com.example.soft.repository.OrderRepository;
import com.example.soft.repository.ProductDescriptionRepository;
import com.example.soft.service.ProductDescriptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductDescriptionImpl implements ProductDescriptionService {

    private final static String DESCRIPTION_NOT_FOUND_MESSAGE = "Not found in Database ProductDescription with Id= ";
    private final static String ORDER_NOT_FOUND_MESSAGE = "Not found in Database order with Id= ";

    ProductDescriptionAssembler assembler;
    ProductDescriptionRepository descriptionRepository;
    OrderRepository orderRepository;


    @Override
    public ProductDescriptionDto findProductDescriptionById(long descriptionId) {
        ProductDescriptionEntity description = descriptionRepository.findById(descriptionId).orElse(null);
        if(description==null){
            throw new ProductDescriptionNotFound(DESCRIPTION_NOT_FOUND_MESSAGE+descriptionId);
        }
        return assembler.assemblerFromEntityToDto(description);
    }

    @Override
    public ProductDescriptionDto addProductDescription(ProductDescriptionDto descriptionDto) {
        if(!orderRepository.existsById(descriptionDto.getOrderId())){
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE+descriptionDto.getOrderId());
        }
        ProductDescriptionEntity description = assembler.assemblerFromDtoToEntity(descriptionDto);
        return assembler.assemblerFromEntityToDto(descriptionRepository.save(description));
    }

    @Override
    public ProductDescriptionDto editProductDescription(ProductDescriptionDto descriptionDto) {
        if(!descriptionRepository.existsById(descriptionDto.getId())){
            throw new ProductDescriptionNotFound(DESCRIPTION_NOT_FOUND_MESSAGE+descriptionDto.getId());
        }
        if(!orderRepository.existsById(descriptionDto.getOrderId())){
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE+descriptionDto.getOrderId());
        }
        ProductDescriptionEntity description = assembler.assemblerFromDtoToEntity(descriptionDto);
        return assembler.assemblerFromEntityToDto(descriptionRepository.save(description));
    }

    @Override
    public String deleteProductDescription(long descriptionId) {
        if(!descriptionRepository.existsById(descriptionId)){
            throw new ProductDescriptionNotFound(DESCRIPTION_NOT_FOUND_MESSAGE+descriptionId);
        }
        descriptionRepository.deleteById(descriptionId);
        return "productDescription with Id= "+descriptionId+" deleted";
    }

    @Override
    public List<ProductDescriptionDto> findAllProductDescriptionByOrderId(long orderId) {
        if(!orderRepository.existsById(orderId)){
            throw new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE+orderId);
        }
        return orderRepository.getById(orderId).getDescriptionList().stream()
                .map(s->assembler.assemblerFromEntityToDto(s))
                .collect(Collectors.toList());
    }
}
