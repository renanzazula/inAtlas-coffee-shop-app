package com.standard.coffeeShop.controller.domain.security;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Set<Authority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialNonExpired;
    private boolean enable;

}
