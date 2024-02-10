package com.co.futbolapi.user.models.dtos.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private String code;
    private String message;

}
