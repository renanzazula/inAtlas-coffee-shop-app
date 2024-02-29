package com.standard.coffeeshop.controller.domain;

import lombok.Data;

import java.io.Serializable;

public @Data
class OrderRequestItem implements Serializable {

    private Product product;


}
