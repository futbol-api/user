package com.co.futbolapi.user.user.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * response body for get all users.
 *
 * @author luis.bolivar
 */
@Builder
public record GetAllUserRsDto(List<UserRsDto> users) {

    /**
     * all users available.
     */
}
