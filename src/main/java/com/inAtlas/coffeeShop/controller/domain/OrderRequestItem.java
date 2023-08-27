package com.inAtlas.coffeeShop.controller.domain;

import lombok.Data;

public @Data
class OrderRequestItem extends Base {

    private Long Id;
    private Product product;


}
