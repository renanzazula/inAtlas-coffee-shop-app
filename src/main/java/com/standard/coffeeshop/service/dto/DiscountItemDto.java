package com.standard.coffeeshop.service.dto;

import lombok.Data;

public @Data class DiscountItemDto extends BaseDto {

    private String id;
    private ProductDto product;

}
