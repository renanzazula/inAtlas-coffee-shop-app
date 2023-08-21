package com.inAtlas.coffeeShop.utils.functions.entityToDto;


import com.inAtlas.coffeeShop.service.dto.ProductDto;
import com.inAtlas.coffeeShop.repository.entity.ProductEntity;
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
