package com.inAtlas.coffeeShop.utils.functions.domainToDto;


import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestToOrderRequestDtoAdapter implements Function<OrderRequest, OrderRequestDto> {

    @Override
    public OrderRequestDto apply(OrderRequest orderRequestDto) {
        OrderRequestDto dto = new OrderRequestDto();
        BeanUtils.copyProperties(orderRequestDto, dto);
        return dto;
    }


}
