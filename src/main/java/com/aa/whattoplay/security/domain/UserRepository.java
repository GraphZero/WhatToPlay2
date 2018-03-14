package com.aa.whattoplay.security.domain;

import com.aa.whattoplay.security.infastructure.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(long id);
    Optional<UserEntity> findByUsername(String username);
}
