package com.co.futbolapi.user.models.dtos.rs;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllUserRsDto {

    /**
     * all users available.
     */
    private List<UserRsDto> users;
}
