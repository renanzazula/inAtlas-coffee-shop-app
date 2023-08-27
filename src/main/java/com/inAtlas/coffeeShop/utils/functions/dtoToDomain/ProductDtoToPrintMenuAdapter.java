package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.PrintMenu;
import com.inAtlas.coffeeShop.service.dto.ProductDto;

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
