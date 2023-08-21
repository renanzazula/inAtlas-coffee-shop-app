package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.Discount;
import com.inAtlas.coffeeShop.service.dto.DiscountDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class DiscountDtoToDiscountAdapter implements Function<DiscountDto, Discount> {

    @Override
    public Discount apply(DiscountDto Discount) {
        Discount domain = new Discount();
        BeanUtils.copyProperties(Discount, domain);
        return domain;
    }
}
