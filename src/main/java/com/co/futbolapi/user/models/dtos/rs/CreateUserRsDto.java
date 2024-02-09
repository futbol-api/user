package com.co.futbolapi.user.models.dtos.rs;

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
@Getter
@ToString
public class CreateUserRsDto {
    /**
     * represents that user was created.
     */
    private UUID id;
}
