package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;

import com.inAtlas.coffeeShop.controller.domain.OrderRequestItem;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemEntity;
import com.inAtlas.coffeeShop.service.dto.OrderRequestItemDto;
import com.inAtlas.coffeeShop.utils.functions.DtoToDomainAdapter;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestItemDtoToOrderRequestItemAdapter implements Function<OrderRequestItemDto, OrderRequestItem> {


    @Override
    public OrderRequestItem apply(OrderRequestItemDto orderRequestItemDto) {
        OrderRequestItem orderRequestItem = new OrderRequestItem();
        BeanUtils.copyProperties(orderRequestItemDto, orderRequestItem);
        orderRequestItem.setProduct(DtoToDomainAdapter.productDtoToProductItemOrderAdapter.apply(orderRequestItemDto.getProduct()));
        return orderRequestItem;
    }
}
