package com.standard.coffeeShop.service.security;

import com.standard.coffeeShop.repository.entity.security.AuthorityEntity;
import com.standard.coffeeShop.repository.entity.security.UserEntity;
import com.standard.coffeeShop.repository.security.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername: {}",username);
        UserEntity user = userDetailsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User name: " + username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnable(), user.isAccountNonExpired(), user.isCredentialNonExpired(), user.isAccountNonLocked(), convertToSpringAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<AuthorityEntity> authorities) {
        if (authorities != null && authorities.size() > 0) {
            return authorities.stream().map(AuthorityEntity::getPermission)
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }
}
