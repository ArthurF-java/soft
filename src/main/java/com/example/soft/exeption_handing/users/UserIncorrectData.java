package com.example.soft.exeption_handing.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class UserIncorrectData {
    private String info;

}
