package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.Product;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;

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
