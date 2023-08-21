package com.inAtlas.coffeeShop.utils.functions.entityToDto;


import com.inAtlas.coffeeShop.service.dto.DiscountDto;
import com.inAtlas.coffeeShop.repository.entity.DiscountEntity;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class DiscountEntityToDiscountDtoAdapter implements Function<DiscountEntity, DiscountDto> {

    @Override
    public DiscountDto apply(DiscountEntity DiscountEntity) {
        DiscountDto dto = new DiscountDto();
        BeanUtils.copyProperties(DiscountEntity, dto);
        return dto;
    }


}
