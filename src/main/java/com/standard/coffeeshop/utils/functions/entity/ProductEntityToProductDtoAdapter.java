package com.standard.coffeeshop.utils.functions.entity;


import com.standard.coffeeshop.repository.entity.ProductEntity;
import com.standard.coffeeshop.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class ProductEntityToProductDtoAdapter implements Function<ProductEntity, ProductDto> {

    @Override
    public ProductDto apply(ProductEntity productEntity) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(productEntity,dto);
        return dto;
    }

}
