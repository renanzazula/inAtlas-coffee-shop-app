package com.standard.coffeeshop.controller.domain.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Login {

    @NotNull
    private String userId;

    @NotEmpty
    private String password;


}
