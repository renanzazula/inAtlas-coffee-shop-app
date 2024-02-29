package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
