package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.PrintMenu;
import com.standard.coffeeshop.service.dto.ProductDto;

import java.util.function.Function;

public class ProductDtoToPrintMenuAdapter implements Function<ProductDto, PrintMenu> {

    @Override
    public PrintMenu apply(ProductDto productDto) {
        PrintMenu domain = new PrintMenu();
        domain.setId(productDto.getId());
        domain.setName(productDto.getName());
        domain.setPrice(productDto.getPriceUnit());
        return domain;
    }

}
