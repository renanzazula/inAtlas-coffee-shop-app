package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.LoginSuccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSuccessRepository extends JpaRepository<LoginSuccessEntity, Long> {}
