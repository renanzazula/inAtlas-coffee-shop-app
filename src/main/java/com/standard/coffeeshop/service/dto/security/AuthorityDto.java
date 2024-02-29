package com.standard.coffeeshop.service.dto.security;

import lombok.Data;

import java.io.Serializable;

public @Data class AuthorityDto implements Serializable {

    private String role;
}
