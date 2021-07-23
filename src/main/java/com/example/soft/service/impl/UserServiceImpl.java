package com.example.soft.service.impl;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.assembler.UserFieldAssembler;
import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import com.example.soft.repository.UserRepository;
import com.example.soft.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserFieldAssembler userFieldAssembler;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<CustomerDto> findAllByRoleCustomer() {
        List <User> userList= userRepository.findAllByRole(Role.ROLE_CUSTOMER);
        return userList.stream().map(s->userFieldAssembler.assemblerFromUserToCustomerDto(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsersByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public CustomerDto findCustomerById(long userId) {
        User user = userRepository.findByRoleAndId(Role.ROLE_CUSTOMER, userId);
        return userFieldAssembler.assemblerFromUserToCustomerDto(user);
    }

    @Override
    public User findByPhone(String username) {
        return userRepository.findByPhone(username);

    }

    @Override
    public User addUser(User user) {
        if(user.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        User user = userFieldAssembler.assemblerFromCustomerDtoToUser(customerDto);
        if(userRepository.existsUserByPhone(customerDto.getPhone())){
            /// EXEPTION PHONE NUMBER not UNIQUE
        }
        User resultUser = userRepository.save(user);
        return userFieldAssembler.assemblerFromUserToCustomerDto(resultUser);
    }

    @Override
    public CustomerDto editCustomer(CustomerDto customerDto) {
        User userCheck = userRepository.findById(customerDto.getId()).orElse(null);
        if(userCheck==null){
            /// EXEPTION USer with  ID not FOUND
        }else{
            if(!customerDto.getPhone().equals(userCheck.getPhone())){
                if(userRepository.existsUserByPhone(customerDto.getPhone())){
                    /// EXEPTION PHONE NUMBER not UNIQUE
                }
            }
        }
        userRepository.save(userFieldAssembler.assemblerFromCustomerDtoToUser(customerDto));
        return customerDto;
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }


}
