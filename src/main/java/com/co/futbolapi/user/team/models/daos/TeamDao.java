package com.co.futbolapi.user.team.models.daos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * db entity to get a team.
 *
 * @author luis.bolivar.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class TeamDao {
    /**
     * team identifier.
     */
    private UUID id;

    /**
     * name of the team.
     */
    private String name;
}