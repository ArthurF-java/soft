package com.example.soft.dto.assembler;

import com.example.soft.dto.UserDto;
import com.example.soft.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFieldAssembler {

    public UserEntity assemble(UserDto userDto) {
        UserEntity user = null;
        if (userDto != null) {
            user = new UserEntity();
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
        }
        return user;
    }

    public UserEntity assemble(UserDto userDto, UserEntity existed) {
        UserEntity user = null;
        if (userDto != null) {
            user = new UserEntity();
            user.setId(userDto.getId() != null ?
                    userDto.getId() : existed.getId());
            user.setFirstName(userDto.getFirstName() != null ?
                    userDto.getFirstName() : existed.getFirstName());
            user.setLastName(userDto.getLastName() != null ?
                    userDto.getLastName() : existed.getLastName());
            user.setPhone(userDto.getPhone() != null ?
                    userDto.getPhone() : existed.getPhone());
            user.setPassword(userDto.getPassword() != null ?
                    userDto.getPassword() : existed.getPassword());
            user.setRole(userDto.getRole() != null ?
                    userDto.getRole() : existed.getRole());
            user.setEmail(userDto.getEmail() != null ?
                    userDto.getEmail() : existed.getEmail());
            user.setCity(userDto.getCity() != null ?
                    userDto.getCity() : existed.getCity());
            user.setStreet(userDto.getStreet() != null ?
                    userDto.getStreet() : existed.getStreet());
            user.setHouse(userDto.getHouse() != null ?
                    userDto.getHouse() : existed.getHouse());
            user.setFlat(userDto.getFlat() != null ?
                    userDto.getFlat() : existed.getFlat());
        }
        return user;
    }

    public UserDto disassemble(UserEntity user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
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
        }
        return userDto;
    }

    public UserDto disassembleForOrder(UserEntity employee) {
        UserDto userDto = null;
        if (employee != null) {
            userDto = new UserDto();
            userDto.setId(employee.getId());
            userDto.setFirstName(employee.getFirstName());
            userDto.setLastName(employee.getLastName());
            userDto.setPhone(employee.getPhone());
            userDto.setEmail(employee.getEmail());
        }
        return userDto;
    }
}
