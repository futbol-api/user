package com.co.futbolapi.user.team.services.interfaces;

import com.co.futbolapi.user.team.models.dtos.TeamDtoRs;

import java.util.Optional;

/**
 * team service interface to map the team operations.
 *
 * @author luis.bolivar.
 */
public interface TeamService {

    Optional<TeamDtoRs> findByName(String name);
}
