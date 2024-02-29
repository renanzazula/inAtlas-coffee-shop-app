package com.standard.coffeeshop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public @Data class ConfigParamsDto implements Serializable {

    private boolean google2fa;

}
