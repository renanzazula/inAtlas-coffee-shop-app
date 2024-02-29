package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.Discount;
import com.standard.coffeeshop.service.dto.DiscountDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class DiscountDtoToDiscountAdapter implements Function<DiscountDto, Discount> {

    @Override
    public Discount apply(DiscountDto discount) {
        Discount domain = new Discount();
        BeanUtils.copyProperties(discount, domain);
        return domain;
    }
}
