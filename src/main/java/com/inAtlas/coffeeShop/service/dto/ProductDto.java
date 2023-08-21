package com.inAtlas.coffeeShop.service.dto;

import lombok.Data;

public @Data
class ProductDto extends BaseDto {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Double quantity;
    private Double priceUnit;

}
