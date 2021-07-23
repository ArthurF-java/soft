package com.example.soft.dto.assembler;


import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.User;
import com.example.soft.entity.enumeracion.Role;
import org.springframework.stereotype.Component;


@Component
public class UserFieldAssembler {

    public UserDto assemblerFromUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setCity(user.getCity());
        userDto.setStreet(user.getStreet());
        userDto.setHouse(user.getHouse());
        userDto.setFlat(user.getFlat());
        return userDto;
    }
    public User assemblerFromUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setId(user.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        user.setStreet(userDto.getStreet());
        user.setHouse(userDto.getHouse());
        user.setFlat(userDto.getFlat());
        return  user;

    }

    public CustomerDto assemblerFromUserToCustomerDto(User user){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(user.getId());
        customerDto.setId(user.getId());
        customerDto.setFirstName(user.getFirstName());
        customerDto.setLastName(user.getLastName());
        customerDto.setPhone(user.getPhone());
        customerDto.setEmail(user.getEmail());
        customerDto.setCity(user.getCity());
        customerDto.setStreet(user.getStreet());
        customerDto.setHouse(user.getHouse());
        customerDto.setFlat(user.getFlat());
        return customerDto;
    }

    public User assemblerFromCustomerDtoToUser(CustomerDto customerDto){
        User user = new User();
        user.setId(customerDto.getId());
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setPhone(customerDto.getPhone());
        user.setPassword(null);
        user.setRole(Role.ROLE_CUSTOMER);
        user.setEmail(customerDto.getEmail());
        user.setCity(customerDto.getCity());
        user.setStreet(customerDto.getStreet());
        user.setHouse(customerDto.getHouse());
        user.setFlat(customerDto.getFlat());
        return  user;

    }
}
