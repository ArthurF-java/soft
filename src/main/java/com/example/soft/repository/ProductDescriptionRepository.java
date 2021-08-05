package com.example.soft.repository;

import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.ProductDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescriptionEntity, Long> {

    List<ProductDescriptionEntity> findAllByOrder (OrderEntity orderEntity);
}
