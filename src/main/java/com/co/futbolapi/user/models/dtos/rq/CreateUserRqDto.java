package com.co.futbolapi.user.models.dtos.rq;

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
@Getter
@ToString
@AllArgsConstructor
public class CreateUserRqDto {
    /**
     * user names for create the user.
     */
    private String names;

    /**
     * unique nickname to identify the user.
     */
    private String nickname;
}
