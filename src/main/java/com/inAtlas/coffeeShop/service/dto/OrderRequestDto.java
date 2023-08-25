package com.inAtlas.coffeeShop.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inAtlas.coffeeShop.utils.Constants;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public  @Data
class OrderRequestDto extends BaseDto {

    @JsonFormat(pattern= Constants.PATTERN_DATE_TIME_FORMAT)
    private LocalDateTime orderDateTime;
    private String status;
    private Long totalQuantity;
    private Double totalAmount;
    private Double totalDiscount;
    private List<OrderRequestItemDto> orderItems;
}
