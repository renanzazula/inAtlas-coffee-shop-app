package com.inAtlas.coffeeShop.utils.functions.entityToDto;

import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemEntity;
import com.inAtlas.coffeeShop.service.dto.OrderRequestItemDto;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
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
