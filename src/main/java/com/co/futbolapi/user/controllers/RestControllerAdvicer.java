package com.co.futbolapi.user.controllers;


import com.co.futbolapi.user.models.dtos.errors.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvicer {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity <ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
        ErrorDTO error = ErrorDTO.builder().code("P.400").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(value = NicknameFalsoException.class)
    public ResponseEntity<ErrorDTO> nicknameFalsoExceptionHandler(NicknameFalsoException ex) {
        ErrorDTO error = ErrorDTO.builder().code("P.400.1").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/
}
