package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.PrintReceipt;
import com.inAtlas.coffeeShop.controller.domain.Product;
import com.inAtlas.coffeeShop.service.dto.PrintReceiptDto;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
import com.inAtlas.coffeeShop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintReceiptDtoToPrintReceiptAdapter implements Function<PrintReceiptDto, PrintReceipt> {

    @Override
    public PrintReceipt apply(PrintReceiptDto printReceiptDto) {
        PrintReceipt domain = new PrintReceipt();
        BeanUtils.copyProperties(printReceiptDto, domain);
        domain.setReceiptItems(printReceiptDto
                                .getReceiptItems().stream()
                                    .map(DtoToDomainAdapter.printReceiptItemDtoToPrintReceiptItemAdapter)
                                        .collect(Collectors.toList()));
        return domain;
    }

}
