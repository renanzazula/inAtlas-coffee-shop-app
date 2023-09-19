package com.standard.coffeeShop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class DiscountDto extends BaseDto {

    private Long id;
    private String title;
    private String status;
    private Date fromDate;
    private Date toDate;
    private Double amountFrom;
    private Double amountTo;
    private Double discount;
    private Double quantityItems;
    private String discountType;

    private List<DiscountItemDto> discountItems = new ArrayList<>();
    private List<DiscountItemDto> comboItems = new ArrayList<>();

}
