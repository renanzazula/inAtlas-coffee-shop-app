package com.inAtlas.coffeeShop.utils.functions.entityToDto;


import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestEntityToOrderRequestDtoAdapter implements Function<OrderRequestEntity, OrderRequestDto> {


    @Override
    public OrderRequestDto apply(OrderRequestEntity orderRequestEntity) {
        OrderRequestDto dto = new OrderRequestDto();
        BeanUtils.copyProperties(orderRequestEntity, dto);
        return dto;
    }


}
