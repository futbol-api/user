package com.co.futbolapi.user.models.repositories;

import com.co.futbolapi.user.models.daos.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository implementation od user.
 *
 * @author luis.bolivar
 * @see UserDao
 */
@Repository
public interface UserRepository extends CrudRepository<UserDao, UUID> {
}
