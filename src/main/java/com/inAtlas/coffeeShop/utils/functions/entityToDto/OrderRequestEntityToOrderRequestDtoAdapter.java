package com.inAtlas.coffeeShop.utils.functions.entityToDto;


import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemEntity;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.service.dto.OrderRequestItemDto;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
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
