package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
