package com.standard.coffeeshop.controller.domain.security;

import lombok.Data;

import java.io.Serializable;

public @Data class Authority implements Serializable {
    private String role;
}
