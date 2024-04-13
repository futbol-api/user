package com.co.futbolapi.user.controllers;

import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.*;
import com.co.futbolapi.user.services.interfaces.UserService;
import com.co.futbolapi.user.services.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserControllerTests {
    @Test
    public void create_userHappyPathTest() {
        // Arrange
        CreateUserRqDto userRq = new CreateUserRqDto("pepe","jonas");

        UserService userService = Mockito.mock(UserService.class);

        UserController userController = new UserController(userService);
        CreateUserRsDto createUserRsDto = new CreateUserRsDto(UUID.randomUUID());


        Mockito.when(userService.create(userRq)).thenReturn(Optional.of(createUserRsDto));

        // Act
        ResponseEntity<CreateUserRsDto> response = userController.create(userRq);

        // Assert
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(createUserRsDto, response.getBody());
    }
     /*@Test
    public void tgetUserByIdHappyPathTest() {
        // Arrange
        UserService userService = Mockito.mock(UserService.class);
        UserController userController = Mockito.mock(UserController.class);
        UUID id = UUID.randomUUID();
        GetUserRsDTO userDto = new GetUserRsDTO(UUID.randomUUID(), null);

        Optional<GetUserRsDTO> userOptional = Optional.of(userDto);
        when(userService.getUserById(id)).thenReturn(userOptional);

        // Act
        ResponseEntity<GetUserRsDTO> response = userController.getUserById(id);

        // Assert

        assertEquals(userDto, response.getBody());
    }

   @Test
    public void test_getAll_usersExist() {
        // Arrange
        UserService userService = Mockito.mock(UserService.class);
        UserController userController = Mockito.mock(UserController.class);
        List<UserRsDto> users = new ArrayList<>();
        users.add(new UserRsDto(UUID.randomUUID(),"John", "Doe"));
        GetAllUserRsDto getAllUserRsDto = new GetAllUserRsDto(users);
        Optional<GetAllUserRsDto> optionalGetAllUserRsDto = Optional.of(getAllUserRsDto);
        when(userService.getAll()).thenReturn(optionalGetAllUserRsDto);

        // Act
        ResponseEntity<GetAllUserRsDto> response = userController.getAll();

        // Assert

        assertEquals(getAllUserRsDto, response.getBody());
    }

    @Test
    public void test_valid_nickname() {
        // Arrange
        UserService userService = Mockito.mock(UserService.class);
        UserController controller = Mockito.mock(UserController.class);

        String nickname = "valid_nickname";
        GetUserRsDTO userDto = new GetUserRsDTO(UUID.randomUUID(),"pepe");

        Optional<GetUserRsDTO> userOptional = Optional.of(userDto);
        when(userService.findByNickname(nickname)).thenReturn(userOptional);

        // Act
        ResponseEntity<GetUserRsDTO> response = controller.getUserByNickname(nickname);

        // Assert

        assertEquals(userDto, response.getBody());
    }*/

    @org.junit.Test
    public void test_valid_nickname_deletion() {
        // Arrange

        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);

        String nickname = "valid_nickname";
        DeleteUserRsDto deleteUserRsDto = new DeleteUserRsDto("f");
        Optional<DeleteUserRsDto> userDeleted = Optional.of(deleteUserRsDto);
        when(userService.deleteByNickname(nickname)).thenReturn(userDeleted);

        // Act
        ResponseEntity<DeleteUserRsDto> response = userController.deleteByNickname(nickname);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deleteUserRsDto, response.getBody());
        verify(userService, times(1)).deleteByNickname(nickname);
    }
}
