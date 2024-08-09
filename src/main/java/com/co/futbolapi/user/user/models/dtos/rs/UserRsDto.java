package com.co.futbolapi.user.user.models.dtos.rs;

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
public record UserRsDto (UUID id, String nickname, String names) {

}
