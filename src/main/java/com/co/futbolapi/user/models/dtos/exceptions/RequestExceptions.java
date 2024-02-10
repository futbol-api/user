package com.co.futbolapi.user.models.dtos.exceptions;

import lombok.Data;


@Data
public class RequestExceptions extends RuntimeException{

    private String code;
    public RequestExceptions(String  code, String message){
        super(message);
        this.code = code;

    }
}
