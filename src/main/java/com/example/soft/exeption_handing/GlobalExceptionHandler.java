package com.example.soft.exeption_handing;

import com.example.soft.exeption_handing.users.PhoneNoUniqueException;
import com.example.soft.exeption_handing.users.UserIncorrectData;
import com.example.soft.exeption_handing.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    UserIncorrectData userIncorrectData;

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> userHandleException(UserNotFoundException exception){
        userIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> userHandleException(PhoneNoUniqueException exception){
        userIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> userHandleException(Exception exception){
        userIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.BAD_REQUEST);
    }



}
