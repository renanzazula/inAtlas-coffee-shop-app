package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.PrintReceiptItem;
import com.standard.coffeeshop.service.dto.PrintReceiptItemDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class PrintReceiptItemDtoToPrintReceiptItemAdapter implements Function<PrintReceiptItemDto, PrintReceiptItem> {

    @Override
    public PrintReceiptItem apply(PrintReceiptItemDto printReceiptItemDto) {
        PrintReceiptItem domain = new PrintReceiptItem();
        BeanUtils.copyProperties(printReceiptItemDto, domain);
        return domain;
    }

}
