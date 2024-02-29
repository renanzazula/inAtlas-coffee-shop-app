package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.PrintReceipt;
import com.standard.coffeeshop.service.dto.PrintReceiptDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
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
