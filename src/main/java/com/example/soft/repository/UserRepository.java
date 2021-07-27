package com.example.soft.repository;

import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
    UserEntity findByPhone(String phone);
    UserEntity findByRoleAndId(Role role, long userId);
    List<UserEntity> findAllByRole(Role role);
    Boolean existsUserByPhone (String phone);


}
