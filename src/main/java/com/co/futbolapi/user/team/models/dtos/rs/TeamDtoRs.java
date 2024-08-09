package com.co.futbolapi.user.team.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * response entity to get a team.
 *
 * @author luis.bolivar.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TeamDtoRs {

    /**
     * team identifier.
     */
    private UUID id;

    /**
     * name of the team.
     */
    private String name;
}
