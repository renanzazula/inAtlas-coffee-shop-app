package com.inAtlas.coffeeShop.utils.functions.entityToDto;


import com.inAtlas.coffeeShop.repository.entity.OrderHasProductEntity;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.repository.entity.ProductEntity;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
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
        List<ProductDto> productDtoList = new ArrayList<>();
        for (OrderHasProductEntity orderHasProductEntity: orderRequestEntity.getOrderHasProduct()) {

//            dto.setProducts(orderRequestEntity
//                    .getOrderHasProduct()
//                    .stream()
//                    .map(OrderHasProductEntity::getProduct)
//                    .collect(Collectors.toList()).stream()
//                    .map(EntityToDtoAdapter.productEntityToProductDtoAdapter)
//                    .collect(Collectors.toList()));

            productDtoList.add(EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(orderHasProductEntity.getProduct()));
        }
        dto.setProducts(productDtoList);
        return dto;
    }


}
