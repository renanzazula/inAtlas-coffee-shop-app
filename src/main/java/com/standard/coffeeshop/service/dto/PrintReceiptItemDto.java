package com.standard.coffeeshop.service.dto;

import lombok.Data;

public @Data
class PrintReceiptItemDto {


    private long amount;
    private String productName;
    private double unitPrice;
    private double total;
}
