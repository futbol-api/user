package com.co.futbolapi.user.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * the user to send to consumers.
 *
 * @author luis.bolivar
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRsDto {
    private UUID id;
    private String nickname;
    private String names;
}
