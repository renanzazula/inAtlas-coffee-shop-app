package com.standard.coffeeShop.repository.security;

import com.standard.coffeeShop.repository.entity.security.LoginFailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailureEntity, Long> {

    List<LoginFailureEntity> findAllByUsernameAndCreatedDateAfter(String username, Timestamp timestamp);
}
