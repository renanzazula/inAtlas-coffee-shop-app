package com.standard.coffeeshop.utils.functions.dtoToDomain;


import com.standard.coffeeshop.controller.domain.OrderRequest;
import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
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
