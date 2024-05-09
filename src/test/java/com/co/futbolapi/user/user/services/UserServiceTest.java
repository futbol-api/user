package com.co.futbolapi.user.user.services;

import com.co.futbolapi.user.user.models.daos.UserDao;
import com.co.futbolapi.user.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.user.models.dtos.rs.DeleteUserRsDto;
import com.co.futbolapi.user.user.models.dtos.rs.GetUserRsDTO;
import com.co.futbolapi.user.user.models.dtos.rs.UserRsDto;
import com.co.futbolapi.user.user.models.repositories.UserRepository;
import com.co.futbolapi.user.user.services.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
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

        when(userRepository.findByNicknameEquals(Mockito.anyString()))
                .thenReturn(Optional.of(userDao));

        final Optional<CreateUserRsDto> result = userService.create(request);
        assertNotNull(request);
        Mockito.verify(userRepository, times(1)).findByNicknameEquals(Mockito.anyString());
        Assertions.assertEquals(Boolean.TRUE, result.isPresent(), "The optional is not empty.");
        Assertions.assertNotNull(result.get().getId(), "The id is not null.");
        Assertions.assertEquals(id, result.get().getId(), "The id is the same");
    }
/*
    @Test
    public void testCreateUser_Success() {

        CreateUserRqDto userRqDto = CreateUserRqDto.builder()
                .names("Alice Smith")
                .nickname("alice")
                .build();

        UserDao savedUser = UserDao.builder()
                .id(UUID.randomUUID())
                .names("Alice Smith")
                .nickname("alice")
                .build();
        Mockito.when(userService.save(Mockito.any(UserDao.class))).thenReturn(Optional.of(savedUser));
        // Act
        userService.create(userRqDto);

        Mockito.verify(userService, Mockito.times(1)).save(
                UserDao.builder()
                        .names(userRqDto.getNames())
                        .nickname(userRqDto.getNickname())
                        .build());

    }*/

    @Test
    public void saved_userHappyPathTest() {
        // Arrange
        UserDao user = UserDao.builder()
                .id(null)
                .names("John Doe")
                .nickname("johndoe")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        // Act
        Optional<UserDao> result = userService.save(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void getUserByIdHappyPathTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        UserDao user = UserDao.builder()
                .id(id)
                .nickname("testUser")
                .build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<GetUserRsDTO> result = userService.getUserById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("testUser", result.get().getNickname());
    }

    @Test
    public void getAllFromDbHappyPathTest() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        List<UserDao> userDaoList = new ArrayList<>();
        userDaoList.add(UserDao.builder()
                .id(UUID.randomUUID())
                .names("John Doe")
                .nickname("johndoe")
                .build());
        when(userRepository.findAll()).thenReturn(userDaoList);

        UserServiceImpl userService = new UserServiceImpl(userRepository);
        Optional<List<UserRsDto>> result = userService.getAllFromDb();

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals("John Doe", result.get().get(0).getNames());
        assertEquals("johndoe", result.get().get(0).getNickname());
    }

    @Test
    public void deleteUserWithValidNicknameHappyPathTest() {
        // Arrange
        String nickname = "valid_nickname";
        UserDao user = UserDao.builder()
                .id(UUID.randomUUID())
                .names("John Doe")
                .nickname(nickname)
                .build();
        when(userRepository.findByNicknameEquals(nickname)).thenReturn(Optional.of(user));

        // Act
        Optional<DeleteUserRsDto> result = userService.deleteByNickname(nickname);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("User " + nickname + " deleted correctly.", result.get().getMessage());
        Mockito.verify(userRepository, times(1)).deleteById(user.getId());
    }

}
