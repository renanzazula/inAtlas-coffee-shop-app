package com.standard.coffeeShop.service.dto.security;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

public @Data class UserDto implements Serializable {

    private String username;
    private Set<AuthorityDto> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialNonExpired;
    private boolean enable;

}
