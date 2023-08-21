package com.inAtlas.coffeeShop.utils.functions.domainToDto;


import com.inAtlas.coffeeShop.controller.domain.Discount;
import com.inAtlas.coffeeShop.service.dto.DiscountDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class DiscountToDiscountDtoAdapter implements Function<Discount, DiscountDto> {

    @Override
    public DiscountDto apply(Discount discount) {
        DiscountDto domain = new DiscountDto();
        BeanUtils.copyProperties(discount, domain);
        return domain;
    }
}
