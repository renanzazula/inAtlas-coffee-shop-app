package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.PrintReceipt;
import com.inAtlas.coffeeShop.controller.domain.PrintReceiptItem;
import com.inAtlas.coffeeShop.service.dto.PrintReceiptDto;
import com.inAtlas.coffeeShop.service.dto.PrintReceiptItemDto;
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
