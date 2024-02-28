package com.co.futbolapi.user.models.repositories;

import com.co.futbolapi.user.models.daos.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;


import java.util.*;


/**
 * Repository implementation od user.
 *
 * @author luis.bolivar
 * @see UserDao
 */
@Repository
public interface UserRepository extends CrudRepository<UserDao, UUID> {


    /**
     * get all users from db.
     * @return list of users.
     */

    List<UserDao> findAll();
    Optional <UserDao> getUserByNickname(String nickname);

}
