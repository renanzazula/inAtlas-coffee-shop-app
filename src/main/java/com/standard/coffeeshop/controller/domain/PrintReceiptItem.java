package com.standard.coffeeshop.controller.domain;

import lombok.Data;

import java.io.Serializable;

public @Data
class PrintReceiptItem  implements Serializable {

    private long amount;
    private String productName;
    private double unitPrice;
}
