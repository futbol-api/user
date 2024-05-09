package com.co.futbolapi.user.team.models.repos;

import com.co.futbolapi.user.team.models.daos.TeamDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo {
    Optional<TeamDao> findByName(String name);
}
