package com.inAtlas.coffeeShop.service.dto;

import lombok.Data;

public @Data class DiscountItemDto extends BaseDto {

    private Long Id;
    private ProductDto product;

}
