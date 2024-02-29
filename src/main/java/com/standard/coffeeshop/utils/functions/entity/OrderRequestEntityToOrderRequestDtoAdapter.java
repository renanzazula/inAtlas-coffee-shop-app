package com.standard.coffeeshop.utils.functions.entity;


import com.standard.coffeeshop.repository.entity.OrderRequestEntity;
import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
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
