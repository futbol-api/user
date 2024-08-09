package com.co.futbolapi.user.team.services;

import com.co.futbolapi.user.team.models.daos.TeamDao;
import com.co.futbolapi.user.team.models.dtos.rs.TeamDtoRs;
import com.co.futbolapi.user.team.models.repos.TeamRepo;
import com.co.futbolapi.user.team.services.services.TeamServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    /**
     * class where inject the mocks.
     */
    @InjectMocks
    private TeamServiceImpl teamService;

    /**
     * mock of {@link TeamRepo}.
     */
    @Mock
    private TeamRepo teamRepo;


    /**
     * happy path.
     */
    @Test //given //when //then
    void givenNameWhenRetrieveTeamByNameThenRetrieveTeamOk() {
        final String name = "Bayer Munich";

        final TeamDao team = TeamDao.builder()
                        .id(UUID.randomUUID())
                                .name(name)
                                        .build();

        //Mockito.when(teamRepo.findByName(Mockito.anyString()))
        //        .thenReturn(Optional.of(team));

        final Optional<TeamDtoRs> result = teamService.findByName(name);

        Assertions.assertNotNull(result, "the result is not null.");
        Assertions.assertTrue(result.isPresent(), "The value is present.");
        Assertions.assertNotNull(result.get().getId(), "The id is not null");
        Assertions.assertNotNull(result.get().getName(), "The name is not null");
        Assertions.assertFalse(result.get().getName().isEmpty(), "The name is not empty.");
        Assertions.assertFalse(result.get().getName().isBlank(), "The name is not blank.");
        Assertions.assertEquals(name, result.get().getName(), "The name is the same.");
    }

    /**
     * not happy path, name is null
     */
    @Test //given //when //then
    void givenNameNullWhenRetrieveTeamByNameThenRetrieveTeamNok() {
        final String name = null; //name is null.

        //Mockito.when(teamRepo.findByName(Mockito.anyString()))
        //        .thenReturn(Optional.of(team));

        final Optional<TeamDtoRs> result = teamService.findByName(name);

        Assertions.assertNotNull(result, "the result is not null.");
        Assertions.assertFalse(result.isPresent(), "The value is not present.");
    }
}
