package com.standard.coffeeShop.utils.functions.dtoToDomain;

import com.standard.coffeeShop.controller.domain.OrderRequestItem;
import com.standard.coffeeShop.service.dto.OrderRequestItemDto;
import com.standard.coffeeShop.utils.functions.DtoToDomainAdapter;
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
