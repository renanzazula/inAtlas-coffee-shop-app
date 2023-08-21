package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.Product;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
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
