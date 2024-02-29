package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.LoginFailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailureEntity, Long> {

    List<LoginFailureEntity> findAllByUserIdAndCreatedDateAfter(Long username, Timestamp timestamp);
}
