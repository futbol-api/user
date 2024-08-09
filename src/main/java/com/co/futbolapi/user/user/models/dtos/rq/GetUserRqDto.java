package com.co.futbolapi.user.user.models.dtos.rq;


import lombok.Builder;
import java.util.UUID;

@Builder
public record GetUserRqDto(UUID id, String nickname) {

    /**
     * unique nickname to identify the user.
     */
}
