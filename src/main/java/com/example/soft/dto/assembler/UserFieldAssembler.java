package com.example.soft.dto.assembler;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.User;

public class UserFieldAssembler {

    public static UserDto assemblerToCustomerDto(User user){
        UserDto userDto = new UserDto();
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
}
