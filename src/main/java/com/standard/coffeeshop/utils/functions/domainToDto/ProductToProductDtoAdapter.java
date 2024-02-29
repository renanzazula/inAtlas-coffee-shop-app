package com.standard.coffeeshop.utils.functions.domainToDto;


import com.standard.coffeeshop.controller.domain.Product;
import com.standard.coffeeshop.service.dto.ProductDto;
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
