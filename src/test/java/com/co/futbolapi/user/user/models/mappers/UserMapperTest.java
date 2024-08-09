package com.co.futbolapi.user.user.models.mappers;


import com.co.futbolapi.user.user.models.daos.UserDao;
import com.co.futbolapi.user.user.models.dtos.rs.GetUserRsDto;
import com.co.futbolapi.user.user.models.dtos.rs.UserRsDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @Test
    public void UsersDaoEmptyTest() {
        List<UserDao> userDaos = new ArrayList<>();
        Optional<List<UserRsDto>> result = UserMapper.fromUsersDao(userDaos);
        assertTrue(result.isEmpty());
    }

    @Test
    public void usersDaoValidTest() {
        List<UserDao> userDaos = new ArrayList<>();
        userDaos.add(UserDao.builder().id(UUID.randomUUID()).names("John Doe").nickname("john").build());
        userDaos.add(UserDao.builder().id(UUID.randomUUID()).names("Jane Smith").nickname("jane").build());
        userDaos.add(UserDao.builder().id(UUID.randomUUID()).names("Bob Johnson").nickname("bob").build());

        Optional<List<UserRsDto>> result = UserMapper.fromUsersDao(userDaos);

        assertTrue(result.isPresent());
        assertEquals(userDaos.size(), result.get().size());
    }

    @Test
    public void userDaoValidTest() {
        // Arrange
        UserDao userDao = new UserDao(UUID.randomUUID(), null,"nickname");
        // Act
        Optional<UserRsDto> result = UserMapper.fromUserDao(userDao);

        // Assert
        Assert.assertTrue(result.isPresent());
        assertEquals(userDao.getId(), result.get().id());
        assertEquals(userDao.getNickname(), result.get().nickname());
        assertNull(result.get().names());
    }

    @Test
    public void getUserRsDTOFromUserDaoTest() {
        // Arrange
        UserDao userDao = UserDao.builder()
                .id(UUID.randomUUID())
                .names("John Doe")
                .nickname("johndoe")
                .build();

        // Act
        Optional<GetUserRsDto> result = UserMapper.getUserRsDTOFromUserDao(userDao);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userDao.getId(), result.get().id());
        assertEquals(userDao.getNickname(), result.get().nickname());
    }

    @Test
    public void emptyOptional_getUserRsDTOFromUserDaoTest() {
        // Arrange
        UserDao userDao = new UserDao(null, null, null);

        // Act
        Optional<GetUserRsDto> result = UserMapper.getUserRsDTOFromUserDao(userDao);

        // Assert
        assertFalse(result.isPresent());
    }

}
