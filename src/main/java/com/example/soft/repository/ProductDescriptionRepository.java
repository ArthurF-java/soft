package com.example.soft.repository;

import com.example.soft.entity.ProductDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescriptionEntity, Long> {
}
