package com.example.soft.service.impl;

import com.example.soft.dto.ProductDescriptionDto;
import com.example.soft.dto.assembler.ProductDescriptionAssembler;
import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.ProductDescriptionEntity;
import com.example.soft.exeption.OrderNotFoundException;
import com.example.soft.exeption.ProductDescriptionNotFound;
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

    private final ProductDescriptionAssembler assembler;
    private final ProductDescriptionRepository descriptionRepository;
    private final OrderRepository orderRepository;

    private final static String DESCRIPTION_NOT_FOUND_MESSAGE = "Not found in Database ProductDescription with Id= ";
    private final static String ORDER_NOT_FOUND_MESSAGE = "Not found in Database order with Id= ";

    @Override
    public ProductDescriptionDto findProductDescriptionById(long descriptionId) {
        ProductDescriptionEntity description = descriptionRepository.findById(descriptionId).orElseThrow(
                () -> new ProductDescriptionNotFound(DESCRIPTION_NOT_FOUND_MESSAGE + descriptionId));
        return assembler.disassemble(description);
    }

    @Override
    public ProductDescriptionDto createProductDescription(ProductDescriptionDto descriptionDto) {
        OrderEntity order = orderRepository.findById(descriptionDto.getOrderId()).orElseThrow(
                () -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + descriptionDto.getOrderId()));
        ProductDescriptionEntity description = assembler.assemble(descriptionDto, order);
        return assembler.disassemble(descriptionRepository.save(description));
    }

    @Override
    public ProductDescriptionDto updateProductDescription(ProductDescriptionDto descriptionDto) {
        ProductDescriptionEntity existed = descriptionRepository.findById(descriptionDto.getId()).orElseThrow(
                () -> new ProductDescriptionNotFound(DESCRIPTION_NOT_FOUND_MESSAGE + descriptionDto.getId()));
        OrderEntity order = orderRepository.findById(descriptionDto.getOrderId()).orElseThrow(
                () -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + descriptionDto.getOrderId()));
        ProductDescriptionEntity description = assembler.assemble(descriptionDto,order);
        return assembler.disassemble(descriptionRepository.save(description));
    }

    @Override
    public void deleteProductDescription(long descriptionId) {
        descriptionRepository.deleteById(descriptionId);
    }

    @Override
    public List<ProductDescriptionDto> findAllProductDescriptionByOrderId(long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderId));
        return descriptionRepository.findAllByOrder(order).stream().map(assembler::disassemble)
                .collect(Collectors.toList());
    }
}
