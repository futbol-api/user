package com.co.futbolapi.user.models.dtos.rs;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * delete user response dto.
 *
 * @author luis.bolivar
 */
@Builder
@Getter
@ToString
public class DeleteUserRsDto {
    /**
     * message with respect to delete process.
     */
    private String message;
}
