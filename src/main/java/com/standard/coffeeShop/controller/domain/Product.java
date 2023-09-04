package com.standard.coffeeShop.controller.domain;

import lombok.Data;

public @Data
class Product extends Base {

    private Long id;
    private String name;
    private Long quantity;
    private Double priceUnit;

}
