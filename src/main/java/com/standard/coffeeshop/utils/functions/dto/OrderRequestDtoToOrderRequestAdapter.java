package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.OrderRequest;
import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class OrderRequestDtoToOrderRequestAdapter implements Function<OrderRequestDto, OrderRequest> {

    @Override
    public OrderRequest apply(OrderRequestDto dto) {
        OrderRequest orderRequest = new OrderRequest();
        BeanUtils.copyProperties(dto, orderRequest);
        orderRequest.setOrderItems(dto
                .getOrderItems().stream()
                .map(DtoToDomainAdapter.orderRequestItemDtoToOrderRequestItemAdapter)
                .toList());
        return orderRequest;
    }
}
