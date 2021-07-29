package com.example.soft.repository;

import com.example.soft.entity.OrderEntity;
import com.example.soft.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByCustomer(UserEntity customer);
}
