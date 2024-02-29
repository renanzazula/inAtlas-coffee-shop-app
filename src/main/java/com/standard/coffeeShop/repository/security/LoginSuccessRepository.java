package com.standard.coffeeShop.repository.security;

import com.standard.coffeeShop.repository.entity.security.LoginSuccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSuccessRepository extends JpaRepository<LoginSuccessEntity, Long> {}
