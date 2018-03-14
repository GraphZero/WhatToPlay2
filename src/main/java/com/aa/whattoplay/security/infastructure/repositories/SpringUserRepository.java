package com.aa.whattoplay.security.infastructure.repositories;

import com.aa.whattoplay.security.infastructure.entities.UserEntity;
import com.aa.whattoplay.security.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends UserRepository, JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
