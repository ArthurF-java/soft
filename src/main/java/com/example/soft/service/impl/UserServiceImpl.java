package com.example.soft.service.impl;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.dto.assembler.UserFieldAssembler;
import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import com.example.soft.exeption_handing.users.PhoneNoUniqueException;
import com.example.soft.exeption_handing.users.UserAlreadyExist;
import com.example.soft.exeption_handing.users.UserNotFoundException;
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

    private final static String USER_NOT_FOUND_MESSAGE = "Not found in Database user with Id= ";
    private final static String CUSTOMER_NOT_FOUND_MESSAGE = "Not found in Database customer with Id= ";
    private final static String USER_EXIST_MESSAGE = "User already exist ind Database with Id= ";
    private final static String USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE = " This phone number of user not unique in Database";

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long userId) {
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE+userId);
        }
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
        if(user==null){
            throw new UserNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE+userId);
        }
        return userFieldAssembler.assemblerFromUserToCustomerDto(user);
    }

    @Override
    public User findByPhone(String username) {
        return userRepository.findByPhone(username);

    }

    @Override
    public UserDto addUser(UserDto userDto) {
        if(userRepository.existsUserByPhone(userDto.getPhone())){
            throw new PhoneNoUniqueException(USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
        }
        User user = userFieldAssembler.assemblerFromUserDtoToUser(userDto);
        if(user.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userFieldAssembler.assemblerFromUserToUserDto(userRepository.save(user));
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        if(userRepository.existsById(customerDto.getId())){
            throw new UserAlreadyExist(USER_EXIST_MESSAGE+ customerDto.getId());
        }
        User user = userFieldAssembler.assemblerFromCustomerDtoToUser(customerDto);
        if(userRepository.existsUserByPhone(customerDto.getPhone())){
            throw new PhoneNoUniqueException(customerDto.getPhone()+USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
        }
        User resultUser = userRepository.save(user);
        return userFieldAssembler.assemblerFromUserToCustomerDto(resultUser);
    }

    @Override
    public CustomerDto editCustomer(CustomerDto customerDto) {
        User userCheck = userRepository.findById(customerDto.getId()).orElse(null);
        if(userCheck==null){
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE+customerDto.getId());
        }else{
            if(!customerDto.getPhone().equals(userCheck.getPhone())){
                if(userRepository.existsUserByPhone(customerDto.getPhone())){
                    throw new PhoneNoUniqueException(customerDto.getPhone()+USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
                }
            }
        }
        userRepository.save(userFieldAssembler.assemblerFromCustomerDtoToUser(customerDto));
        return customerDto;
    }

    @Override
    public void deleteUser(long userId) {
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE+userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto editUser(UserDto userDto) {
        User userCheck = userRepository.findById(userDto.getId()).orElse(null);
        if(userCheck==null){
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE+userDto.getId());
        }else{
            if(!userDto.getPhone().equals(userCheck.getPhone())){
                if(userRepository.existsUserByPhone(userDto.getPhone())){
                    throw new PhoneNoUniqueException(userDto.getPhone()+USER_PHONE_NUMBER_NOT_UNIQUE_MESSAGE);
                }
            }
        }
        userRepository.save(userFieldAssembler.assemblerFromUserDtoToUser(userDto));
        return userDto;
    }
}
