package com.standard.coffeeShop.utils.functions.entityToDto;


import com.standard.coffeeShop.repository.entity.OrderRequestEntity;
import com.standard.coffeeShop.service.dto.OrderRequestDto;
import com.standard.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderRequestEntityToOrderRequestDtoAdapter implements Function<OrderRequestEntity, OrderRequestDto> {


    @Override
    public OrderRequestDto apply(OrderRequestEntity orderRequestEntity) {
        OrderRequestDto dto = new OrderRequestDto();
        BeanUtils.copyProperties(orderRequestEntity, dto);
        dto.setStatus(orderRequestEntity.getStatus().name());
        dto.setOrderItems(orderRequestEntity
                .getOrderItems().stream()
                .map(EntityToDtoAdapter.orderRequestItemEntityToOrderRequestItemDtoAdapter)
                .collect(Collectors.toList()));
        return dto;
    }


}
