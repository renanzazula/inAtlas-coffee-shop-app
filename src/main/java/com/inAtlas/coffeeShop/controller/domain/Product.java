package com.inAtlas.coffeeShop.controller.domain;

import lombok.Data;

public @Data
class Product extends Base {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Double quantity;
    private Double priceUnit;

}
