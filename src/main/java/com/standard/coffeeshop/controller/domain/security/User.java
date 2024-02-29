package com.standard.coffeeshop.controller.domain.security;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data class User implements Serializable {

    private Integer id;
    private String username;
    private List<Authority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialNonExpired;
    private boolean enable;

}
