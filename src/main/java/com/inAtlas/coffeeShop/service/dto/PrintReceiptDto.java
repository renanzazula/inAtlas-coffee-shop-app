package com.inAtlas.coffeeShop.service.dto;

import lombok.Data;

import java.util.List;

public @Data
class PrintReceiptDto {

    private double total;
    List<PrintReceiptItemDto> receiptItems;

}
