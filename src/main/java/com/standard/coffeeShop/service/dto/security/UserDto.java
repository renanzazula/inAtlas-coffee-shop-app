package com.standard.coffeeShop.service.dto.security;

import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {

    private String username;
    private String password;
    private Set<AuthorityDto> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialNonExpired;
    private boolean enable;

}
