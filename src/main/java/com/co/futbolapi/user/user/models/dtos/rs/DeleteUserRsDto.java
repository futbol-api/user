package com.co.futbolapi.user.user.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * delete user response dto.
 *
 * @author luis.bolivar
 */
@Builder
public record   DeleteUserRsDto (String message) {
    /**
     * message with respect to delete process.
     */
}
