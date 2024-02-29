package com.standard.coffeeshop.controller.domain;

import lombok.Data;

public @Data
class Product extends Base {

    private Long id;
    private String name;
    private Long quantity;
    private Double priceUnit;

}
