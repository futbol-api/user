package com.co.futbolapi.user.team.services.services;

import com.co.futbolapi.user.team.models.dtos.TeamDtoRs;
import com.co.futbolapi.user.team.services.interfaces.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * An Implementation of {@link TeamService}.
 *
 * @author luis.bolivar.
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    @Override
    public Optional<TeamDtoRs> findByName(final String name) {
        return Stream.of(name)
                        .filter(Objects::nonNull)
                        .filter(n -> !n.isEmpty())
                        .filter(n -> !n.isBlank())
                        .findFirst()
                        .map(n -> TeamDtoRs.builder()
                                .id(UUID.randomUUID())
                                .name(n)
                                .build());
    }
}
