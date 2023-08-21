package com.inAtlas.coffeeShop.utils.functions.domainToDto;


import com.inAtlas.coffeeShop.controller.domain.Product;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class ProductToProductDtoAdapter implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product domain) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(domain, dto);
        return dto;
    }

}
