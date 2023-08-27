package com.inAtlas.coffeeShop.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public @Data
class DiscountDto extends BaseDto {

    private Long id;
    private String status;
    private Date fromDate;
    private Date toDate;
    private Double discount;
    private Double quantityItems;
    private String discountType;

    private List<DiscountItemDto> discountItems = new ArrayList<>();

}
