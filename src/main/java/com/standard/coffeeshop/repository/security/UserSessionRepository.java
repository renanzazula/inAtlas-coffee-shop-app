package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, String> {

    UserSessionEntity findByActiveSessions(String attrValue);

    UserSessionEntity findByUserId(String userId);

}
