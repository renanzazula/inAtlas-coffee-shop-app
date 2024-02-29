package com.standard.coffeeshop.utils.functions.dtoToDomain;


import com.standard.coffeeshop.controller.domain.Discount;
import com.standard.coffeeshop.service.dto.DiscountDto;
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
