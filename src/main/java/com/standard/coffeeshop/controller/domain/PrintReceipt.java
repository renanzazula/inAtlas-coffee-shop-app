package com.standard.coffeeshop.controller.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data
class PrintReceipt  implements Serializable {

    private double total;
    private List<PrintReceiptItem> receiptItems;

}
