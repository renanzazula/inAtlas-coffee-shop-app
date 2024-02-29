package com.standard.coffeeshop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class ProductDto extends BaseDto {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Long quantity;
    private Double priceUnit;

}
