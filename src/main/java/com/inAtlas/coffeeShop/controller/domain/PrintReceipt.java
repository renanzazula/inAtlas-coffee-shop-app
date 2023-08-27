package com.inAtlas.coffeeShop.controller.domain;

import lombok.Data;

import java.util.List;

public @Data
class PrintReceipt {

    private double total;
    List<PrintReceiptItem> receiptItems;

}
