package com.co.futbolapi.user.user.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Response that user was created ok.
 *
 * @author luis.bolivar"
 */
@Builder
public record CreateUserRsDto(UUID id) {
    /**
     * represents that user was created.
     */

}
