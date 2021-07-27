package com.example.soft.dto.assembler;

import com.example.soft.dto.CustomerDto;
import com.example.soft.dto.UserDto;
import com.example.soft.entity.UserEntity;
import com.example.soft.entity.enumeracion.Role;
import com.example.soft.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UserFieldAssembler {
    UserRepository userRepository;
    OrderFieldAssembler orderFieldAssembler;

    public UserDto assemblerFromUserToUserDto(UserEntity user) {

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

    public UserEntity assemblerFromUserDtoToUser(UserDto userDto) {

        UserEntity user = null;
        if (userDto != null) {
            user = new UserEntity();
            user.setId(userDto.getId());
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

    public CustomerDto assemblerFromUserToCustomerDto(UserEntity user) {

        CustomerDto customerDto = null;
        if (user != null) {
            customerDto = new CustomerDto();
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
            customerDto.setOrderList(user.getOrders() == null ? null :
                    user.getOrders()
                            .stream()
                            .map(s -> orderFieldAssembler.assemblerFromOrderToOrderDto(s))
                            .collect(Collectors.toList()));
        }
        return customerDto;
    }

    public UserEntity assemblerFromCustomerDtoToUser(CustomerDto customerDto) {
        UserEntity user = null;
        if (customerDto != null) {
            user = new UserEntity();
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
            user.setOrders(customerDto.getId()==null? null :
                    userRepository.existsById(customerDto.getId()) ?
                    userRepository.getById(customerDto.getId()).getOrders() : null);

        }
        return user;
    }
}
