package com.co.futbolapi.user.models.daos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

/**
 * the user dao.
 *
 * @author luis.bolivar
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@RedisHash("User")
public class UserDao {
    /**
     * id of the user.
     */
    private UUID id;
    /**
     * user names for create the user.
     */
    private String names;

    /**
     * unique nickname to identify the user.
     */
    private String nickname;
}
