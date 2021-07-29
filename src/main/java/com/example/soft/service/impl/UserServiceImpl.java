package com.example.soft.service.impl;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.dto.assembler.CustomerFieldAssembler;
import com.example.soft.dto.assembler.UserFieldAssembler;
import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import com.example.soft.exeption.PhoneNoUniqueException;
import com.example.soft.exeption.UserNotFoundException;
import com.example.soft.repository.UserRepository;
import com.example.soft.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFieldAssembler userFieldAssembler;
    private final CustomerFieldAssembler customerFieldAssembler;

    private final static String USER_NOT_FOUND_MESSAGE = "Not found in Database user with Id = ";
    private final static String CUSTOMER_NOT_FOUND_MESSAGE = "Not found in Database customer with Id = ";
    private final static String USER_PHONE_NOT_NULL = "User phone number mustn't be null";
    private final static String USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE = " This phone number of user not unique in Database";

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userFieldAssembler::disassemble)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + userId));
        return userFieldAssembler.disassemble(user);
    }

    @Override
    public List<CustomerDto> findAllByRoleCustomer() {
        List<UserEntity> userList = userRepository.findAllByRole(Role.ROLE_CUSTOMER);
        return userList.stream().map(customerFieldAssembler::disassemble)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(long userId) {
        UserEntity user = userRepository.findByRoleAndId(Role.ROLE_CUSTOMER, userId);
        if (user == null) {
            throw new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE + userId);
        }
        return customerFieldAssembler.disassemble(user);
    }

    @Override
    public UserEntity findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getPhone() == null) {
            throw new PhoneNoUniqueException(USER_PHONE_NOT_NULL);
        }
        if (userRepository.existsUserByPhone(userDto.getPhone())) {
            throw new PhoneNoUniqueException(USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
        }
        UserEntity user = userFieldAssembler.assemble(userDto);
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userFieldAssembler.disassemble(userRepository.save(user));
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        if (customerDto.getPhone() == null) {
            throw new PhoneNoUniqueException(USER_PHONE_NOT_NULL);
        }
        if (userRepository.existsUserByPhone(customerDto.getPhone())) {
            throw new PhoneNoUniqueException(customerDto.getPhone() + USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
        }
        UserEntity user = customerFieldAssembler.assemble(customerDto);
        return customerFieldAssembler.disassemble(userRepository.save(user));
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.delete(userRepository.getById(userId));
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        UserEntity existed = userRepository.findById(customerDto.getId()).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + customerDto.getId()));
        if (!customerDto.getPhone().equals(existed.getPhone())) {
            if (userRepository.existsUserByPhone(customerDto.getPhone())) {
                throw new PhoneNoUniqueException(customerDto.getPhone() + USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
            }
        }
        UserEntity userResult = userRepository.save(customerFieldAssembler.assemble(customerDto, existed));
        return customerFieldAssembler.disassemble(userResult);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity existed = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + userDto.getId()));
        if (!userDto.getPhone().equals(existed.getPhone())) {
            if (userRepository.existsUserByPhone(userDto.getPhone())) {
                throw new PhoneNoUniqueException(userDto.getPhone() + USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
            }
        }
        UserEntity userResult = userRepository.save(userFieldAssembler.assemble(userDto, existed));
        return userFieldAssembler.disassemble(userResult);
    }
}
