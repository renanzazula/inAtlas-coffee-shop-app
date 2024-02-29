package com.standard.coffeeShop.repository.security;

import com.standard.coffeeShop.repository.entity.security.AuthorityEntity;
import com.standard.coffeeShop.repository.entity.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
