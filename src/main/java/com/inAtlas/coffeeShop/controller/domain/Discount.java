package com.inAtlas.coffeeShop.controller.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public @Data
class Discount extends Base {

    private Long id;
    private String status;
    private Date fromDate;
    private Date toDate;
    private Double discount;
    private List<Product> products = new ArrayList<>();

}
