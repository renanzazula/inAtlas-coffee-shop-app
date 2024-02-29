package com.standard.coffeeshop.utils.functions.domainToDto;


import com.standard.coffeeshop.controller.domain.Discount;
import com.standard.coffeeshop.service.dto.DiscountDto;
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
