package com.co.futbolapi.user.services;

import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.models.repositories.UserRepository;
import com.co.futbolapi.user.services.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    /**
     * happy path with exist user.
     */
    @Test
    void givenUserCreateRqDtoWhenCreateUserThenReturnExistUserOk() {
        // given, when, then.
        // red, blue, green.
        final UUID id = UUID.randomUUID();
        final CreateUserRqDto request = CreateUserRqDto.builder()
                .nickname("user")
                .build();

        final UserDao userDao = UserDao.builder()
                .id(id)
                .build();

        Mockito.when(userRepository.findByNicknameEquals(Mockito.anyString()))
                .thenReturn(Optional.of(userDao));

        final Optional<CreateUserRsDto> result = userService.create(request);

        Mockito.verify(userRepository, Mockito.times(1)).findByNicknameEquals(Mockito.anyString());
        Assertions.assertEquals(Boolean.TRUE, result.isPresent(), "The optional is not empty.");
        Assertions.assertNotNull(result.get().getId(), "The id is not null.");
        Assertions.assertEquals(id, result.get().getId(), "The id is the same");
    }

}
