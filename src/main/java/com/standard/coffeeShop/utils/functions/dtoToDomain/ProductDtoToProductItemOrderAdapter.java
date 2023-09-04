package com.standard.coffeeShop.utils.functions.dtoToDomain;


import com.standard.coffeeShop.controller.domain.Product;
import com.standard.coffeeShop.service.dto.ProductDto;

import java.util.function.Function;

public class ProductDtoToProductItemOrderAdapter implements Function<ProductDto, Product> {

    @Override
    public Product apply(ProductDto productDto) {
        Product domain = new Product();
        domain.setId(productDto.getId());
        domain.setName(productDto.getName());
        domain.setPriceUnit(productDto.getPriceUnit());
        return domain;
    }

}
