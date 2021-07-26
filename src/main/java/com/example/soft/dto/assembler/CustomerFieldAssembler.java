package com.example.soft.dto.assembler;
import com.example.soft.dto.CustomerDto;
import com.example.soft.entity.User;

public class CustomerFieldAssembler {

   public static CustomerDto assemblerToCustomerDto(User user){
       CustomerDto customerDto = new CustomerDto();
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
}
