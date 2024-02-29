package com.standard.coffeeShop.repository.security;

import com.standard.coffeeShop.repository.entity.security.AuthorityEntity;
import com.standard.coffeeShop.repository.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

}
