package com.co.futbolapi.user.services.services;

import com.co.futbolapi.user.models.daos.UserDao;
import com.co.futbolapi.user.models.dtos.rq.CreateUserRqDto;
import com.co.futbolapi.user.models.dtos.rs.CreateUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.DeleteUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetAllUserRsDto;
import com.co.futbolapi.user.models.dtos.rs.GetUserRsDTO;
import com.co.futbolapi.user.models.dtos.rs.UserRsDto;
import com.co.futbolapi.user.models.mappers.UserMapper;
import com.co.futbolapi.user.models.repositories.UserRepository;
import com.co.futbolapi.user.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return Stream.of(validateUser(userRq))
                .filter(userRs -> userRs.getId() != null)
                .findFirst();
    }

    /**
     * {@inheritDoc}
     * @param userRq request to create a new user.
     * @return
     */
    @Override
    public CreateUserRsDto validateUser(final CreateUserRqDto userRq) {
        log.info("[validateUser]: rq: {}", userRq.toString());
        return getExistUser(userRq).orElseGet(() -> createUser(userRq));
    }

    private Optional<CreateUserRsDto> getExistUser(CreateUserRqDto userRq) {
        return userRepository.findByNicknameEquals(userRq.getNickname())
                .map(user -> CreateUserRsDto.builder().id(user.getId()).build());
    }

    private CreateUserRsDto createUser(CreateUserRqDto userRq) {
        log.info("[createUser] creating the user: {}", userRq.toString());
        return save(UserDao.builder()
                .names(userRq.getNames())
                .nickname(userRq.getNickname())
                .build())
                .map(user -> CreateUserRsDto.builder().id(user.getId()).build())
                .orElseThrow(() -> new RuntimeException("Error saving user."));
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

    public Optional<List<UserRsDto>> getAllFromDb() {
        Optional<List<UserRsDto>> result = Optional.empty();
        try{
            List<UserDao> daos = userRepository.findAll();
            result= UserMapper.fromUsersDao(daos);
        } catch(Exception e){
            log.error("[save]: Error saving user: {}", e.getMessage());
        }

        return result;
    }

    @Override
    public Optional<GetAllUserRsDto> getAll() {
        return getAllFromDb().map(u -> GetAllUserRsDto.builder().users(u).build());
    }

    @Override
    public Optional<GetUserRsDTO> findByNickname(final String nickname) {
        return userRepository.findByNicknameEquals(nickname).map(UserMapper::getUserRsDTOFromUserDao)
                .orElseThrow(() -> new RuntimeException("User not found by nickname: " + nickname));
    }

    /**
     * {@inheritDoc}
     * @param nickname related to user.
     * @return
     */
    @Override
    public Optional<DeleteUserRsDto> deleteByNickname(String nickname) {
        return userRepository.findByNicknameEquals(nickname).map(user -> {
            log.info("user found.");
            userRepository.deleteById(user.getId());
            return Optional.of(DeleteUserRsDto.builder()
                    .message("User " + nickname + " deleted correctly.")
                    .build());
        }).orElseGet(() -> {
            log.error("[deleteByNickname]: Error deleting user by nickname {}", nickname);
            return Optional.empty();
        });
    }

}
