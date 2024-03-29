package com.co.futbolapi.user.services.interfaces;

import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.DeleteUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetAllUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetUserRsDTO;

import java.util.Optional;
import java.util.UUID;

/**
 * User service.
 *
 * @author luis.bolivar.
 */
public interface UserService {

    /**
     * create a new user from request.
     * @param userRq request to create a new user.
     * @return {@link Optional} of {@link CreateUserRsDto}.
     */
    Optional<CreateUserRsDto> create(CreateUserRqDto userRq);

    /**
     * create a new user from request.
     * @param userRq request to create a new user.
     * @return {@link CreateUserRsDto}.
     */
    CreateUserRsDto validateUser(CreateUserRqDto userRq);

    /**
     * save a user.
     * @param user to save.
     * @return {@link Optional} of {@link UserDao}.
     */
    Optional<UserDao> save(UserDao user);

    Optional<GetUserRsDTO> getUserById(UUID id);

    /**
     * get all users.
     * @return {@link Optional} of {@link GetAllUserRsDto}.
     */
    Optional<GetAllUserRsDto> getAll();

    /**
     * get user by nickname.
     * @param nickname to get user.
     * @return {}@link Optional} of {@link GetUserRsDTO}
     */
    Optional<GetUserRsDTO> findByNickname(String nickname);

    /**
     * delete user by nickname.
     * @param nickname related to user.
     * @return {@link Optional} of {@link DeleteUserRsDto}.
     */
    Optional<DeleteUserRsDto> deleteByNickname(String nickname);
}
