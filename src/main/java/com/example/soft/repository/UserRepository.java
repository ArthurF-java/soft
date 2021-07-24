package com.example.soft.repository;

import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByPhone(String phone);
    User findByRoleAndId(Role role, long userId);
    List<User> findAllByRole(Role role);
    Boolean existsUserByPhone (String phone);


}
