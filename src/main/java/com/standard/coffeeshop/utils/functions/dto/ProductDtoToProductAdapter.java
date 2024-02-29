package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.Product;
import com.standard.coffeeshop.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class ProductDtoToProductAdapter implements Function<ProductDto, Product> {

    @Override
    public Product apply(ProductDto productDto) {
        Product domain = new Product();
        BeanUtils.copyProperties(productDto, domain);
        return domain;
    }

}
