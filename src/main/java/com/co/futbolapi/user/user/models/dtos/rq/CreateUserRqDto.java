package com.co.futbolapi.user.user.models.dtos.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Request to create a user.
 *
 * @author luis.bolivar
 */
@Builder
public record CreateUserRqDto(String names, String nickname) {
    /**
     * user names for create the user.
     */

    /**
     * unique nickname to identify the user.
     */

}
