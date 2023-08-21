package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestDtoToOrderRequestAdapter implements Function<OrderRequestDto, OrderRequest> {

    @Override
    public OrderRequest apply(OrderRequestDto orderRequestDto) {
        OrderRequest domain = new OrderRequest();
        BeanUtils.copyProperties(orderRequestDto, domain);
        return domain;
    }


}
