package com.standard.coffeeShop.controller.domain.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Login {

    private String user;
    private String password;


}
