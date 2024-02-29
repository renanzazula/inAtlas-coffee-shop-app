package com.standard.coffeeshop.utils.functions.entityToDto;


import com.standard.coffeeshop.repository.entity.DiscountEntity;
import com.standard.coffeeshop.service.dto.DiscountDto;
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
