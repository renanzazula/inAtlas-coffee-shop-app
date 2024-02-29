package com.standard.coffeeshop.service.dto;

import lombok.Data;

public @Data class ComboItemDto extends BaseDto {

    private Long Id;
    private ProductDto product;

}
