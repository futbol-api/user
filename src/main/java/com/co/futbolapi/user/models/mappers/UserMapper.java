package com.co.futbolapi.user.models.mappers;


import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.rs.UserRsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * user mapper.
 *
 * @author luis.bolivar
 */
public class UserMapper {

    /**
     * from {@link List} of {@link UserDao} to {@link List} of {@link UserRsDto}
     * @param userDaos to pass to {@link UserRsDto}.
     * @return {@link List} of {@link UserRsDto}.
     */
    public static Optional<List<UserRsDto>> fromUsersDao(final List<UserDao> userDaos){
        final List<UserRsDto> list = new ArrayList<>();
        Optional<List<UserRsDto>>  result = Optional.empty();
        if(userDaos != null && !userDaos.isEmpty()) {
            userDaos.parallelStream().forEach(userDao -> fromUserDao(userDao).ifPresent(list::add));
            result = Optional.of(list);
        }
       return result;
    }

    /**
     *
     * @param userDao
     * @return
     */
    public static Optional<UserRsDto> fromUserDao(final UserDao userDao){
        return Stream.of(userDao)
                        .filter(user -> user.getId() != null)
                        .filter(user -> user.getNickname() != null)
                        .filter(user -> !user.getNickname().isEmpty())
                        .filter(user -> !user.getNickname().isBlank())
                        .map(user -> UserRsDto.builder()
                                .names(user.getNames())
                                .nickname(user.getNickname())
                                .id(user.getId()).build())
                .findFirst();
    }
}
