package com.standard.coffeeshop.utils.functions.dtoToDomain;

import com.standard.coffeeshop.controller.domain.OrderRequestItem;
import com.standard.coffeeshop.service.dto.OrderRequestItemDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
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
