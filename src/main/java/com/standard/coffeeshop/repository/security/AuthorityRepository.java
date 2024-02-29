package com.standard.coffeeshop.repository.security;

import com.standard.coffeeshop.repository.entity.security.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

}
