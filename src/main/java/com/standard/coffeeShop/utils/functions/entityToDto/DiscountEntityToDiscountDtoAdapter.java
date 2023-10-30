package com.standard.coffeeShop.utils.functions.entityToDto;


import com.standard.coffeeShop.repository.entity.DiscountEntity;
import com.standard.coffeeShop.service.dto.DiscountDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class DiscountEntityToDiscountDtoAdapter implements Function<DiscountEntity, DiscountDto> {

    @Override
    public DiscountDto apply(DiscountEntity discountEntity) {
        DiscountDto dto = new DiscountDto();
        BeanUtils.copyProperties(discountEntity, dto);
        return dto;
    }


}
