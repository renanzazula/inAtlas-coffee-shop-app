package com.standard.coffeeShop.utils.functions.dtoToDomain;


import com.standard.coffeeShop.controller.domain.OrderRequest;
import com.standard.coffeeShop.service.dto.OrderRequestDto;
import com.standard.coffeeShop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderRequestDtoToOrderRequestAdapter implements Function<OrderRequestDto, OrderRequest> {

    @Override
    public OrderRequest apply(OrderRequestDto dto) {
        OrderRequest orderRequest = new OrderRequest();
        BeanUtils.copyProperties(dto, orderRequest);
        orderRequest.setOrderItems(dto
                .getOrderItems().stream()
                .map(DtoToDomainAdapter.orderRequestItemDtoToOrderRequestItemAdapter)
                .collect(Collectors.toList()));
        return orderRequest;
    }
}
