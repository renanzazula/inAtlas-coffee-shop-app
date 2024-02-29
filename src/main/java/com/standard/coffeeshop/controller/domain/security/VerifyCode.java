package com.standard.coffeeshop.controller.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public  @Data class VerifyCode implements Serializable {

    private int code;

}
