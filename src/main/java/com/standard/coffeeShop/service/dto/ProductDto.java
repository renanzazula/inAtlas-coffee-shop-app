package com.standard.coffeeShop.service.dto;

import lombok.Data;

public @Data
class ProductDto extends BaseDto {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Long quantity;
    private Double priceUnit;

}
