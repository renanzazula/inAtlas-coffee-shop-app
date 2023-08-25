package com.inAtlas.coffeeShop.controller.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inAtlas.coffeeShop.utils.Constants;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public  @Data
class OrderRequest extends Base {

    @JsonFormat(pattern= Constants.PATTERN_DATE_TIME_FORMAT)
    private LocalDateTime orderDateTime;

    private String status;
    private Long totalQuantity;

    private Double totalAmount;
    private Double totalDiscount;
    private List<OrderRequestItem> orderItems = new ArrayList<>();
}
