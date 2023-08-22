package com.inAtlas.coffeeShop.utils.functions.dtoToDomain;


import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderRequestDtoToOrderRequestAdapter implements Function<OrderRequestDto, OrderRequest> {

    @Override
    public OrderRequest apply(OrderRequestDto dto) {
        OrderRequest domain = new OrderRequest();
        BeanUtils.copyProperties(dto, domain);
        domain.setProducts(dto.getProducts()
                .stream()
                .map(DtoToDomainAdapter
                .productDtoToProductDomainAdapter)
                .collect(Collectors.toList()));
        return domain;
    }


}
