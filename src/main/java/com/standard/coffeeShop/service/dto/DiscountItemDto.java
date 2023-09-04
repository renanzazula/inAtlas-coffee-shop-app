package com.standard.coffeeShop.service.dto;

import lombok.Data;

public @Data class DiscountItemDto extends BaseDto {

    private Long Id;
    private ProductDto product;

}
