package com.standard.coffeeshop.utils.functions.entity;

import com.standard.coffeeshop.repository.entity.OrderRequestItemEntity;
import com.standard.coffeeshop.service.dto.OrderRequestItemDto;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestItemEntityToOrderRequestItemDtoAdapter  implements Function<OrderRequestItemEntity, OrderRequestItemDto> {


    @Override
    public OrderRequestItemDto apply(OrderRequestItemEntity orderRequestItemEntity) {
        OrderRequestItemDto dto = new OrderRequestItemDto();
        BeanUtils.copyProperties(orderRequestItemEntity, dto);
        dto.setProduct(EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(orderRequestItemEntity.getProduct()));
        return dto;
    }
}
