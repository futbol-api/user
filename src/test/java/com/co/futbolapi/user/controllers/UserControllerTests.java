package com.co.futbolapi.user.controllers;

import com.co.futbolapi.user.models.dtos.exceptions.RequestExceptions;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.*;
import com.co.futbolapi.user.services.interfaces.UserService;
import com.co.futbolapi.user.services.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

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

    /*
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
*/

    /**
     * happy path.
     */
    @Test
    public void whenGetAllUserThenGetAllOk() { //given//when//then
        final List<UserRsDto> users = List.of(
                UserRsDto.builder().build(),
                UserRsDto.builder().build()
        );

        final GetAllUserRsDto expected = GetAllUserRsDto.builder().users(users).build();

        Mockito.when(userService.getAll())
                .thenReturn(Optional.of(expected));

        ResponseEntity<GetAllUserRsDto> response = userController.getAll();

        Mockito.verify(userService, Mockito.times(1))
                .getAll();
        Assertions.assertNotNull(response, "the response is no null.");
        Assertions.assertNotNull(response.getBody(), "the response body is not null.");
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful(), "The response status code is ok.");
        Assertions.assertNotNull(response.getBody().getUsers(), "the users are not null.");
        Assertions.assertFalse(response.getBody().getUsers().isEmpty(), "the users are not empty.");
        Assertions.assertEquals(2, response.getBody().getUsers().size(), "the amount user is 2.");
    }

    /**
     * not happy path.
     */
    @Test
    public void whenGetAllUserThenGetAllNok() { //given//when//then
        Mockito.when(userService.getAll())
                .thenReturn(Optional.empty());

        RequestExceptions re = Assertions.assertThrows(RequestExceptions.class,
                () -> userController.getAll());

        Mockito.verify(userService, Mockito.times(1))
                .getAll();
        Assertions.assertNotNull(re, "the response is no null.");
        Assertions.assertNotNull(re.getCode(), "the exception code is not null.");
        Assertions.assertNotNull(re.getMessage(), "the exception message is not null.");
        Assertions.assertEquals("User not found", re.getMessage(), "the same message.");
    }
}
