package com.co.futbolapi.user.services.services;

import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetUserRsDTO;
import com.co.futbolapi.user.models.repositories.UserRepository;
import com.co.futbolapi.user.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * User service to provides behavior for users-
 *
 * @author luis.bolivar
 * @see UserService
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    /**
     * {@inheritDoc}
     * @param userRq request to create a new user.
     * @return
     */
    @Override
    public Optional<CreateUserRsDto> create(final CreateUserRqDto userRq) {
        return Stream.of(createUser(userRq))
                .filter(userRs -> userRs.getId() != null)
                .findFirst();
    }

    /**
     * {@inheritDoc}
     * @param userRq request to create a new user.
     * @return
     */
    @Override
    public CreateUserRsDto createUser(final CreateUserRqDto userRq) {
        log.info("[createUser]: rq: {}", userRq.toString());
        return save(UserDao.builder()
                .names(userRq.getNames())
                .nickname(userRq.getNickname())
                .build())
                .map(user -> CreateUserRsDto.builder().id(user.getId()).build())
                .orElseThrow(() ->new RuntimeException("Error saving user."));
    }

    /**
     * {@inheritDoc}
     * @param user to save.
     * @return
     */
    @Override
    public Optional<UserDao> save(UserDao user) {
        Optional<UserDao> result = Optional.empty();
        try{
            result = Optional.of(userRepository.save(user));
        } catch(Exception e){
            log.error("[save]: Error saving user: {}", e.getMessage());
        }

        return result;
    }

    @Override
    public Optional<GetUserRsDTO> getUserById(UUID id) {
        Optional<UserDao> user = userRepository.findById(id);

        return user.map(u -> GetUserRsDTO.builder()
                .id(u.getId())
                .nickname(u.getNickname())
                .build());
    }
}
