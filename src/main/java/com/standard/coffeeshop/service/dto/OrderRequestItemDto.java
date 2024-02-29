package com.standard.coffeeshop.service.dto;

import lombok.Data;

public @Data class OrderRequestItemDto extends BaseDto {

    private Long Id;
    private Double discount;
    private Double priceUnit;
    private ProductDto product;

}
