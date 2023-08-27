package com.inAtlas.coffeeShop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inAtlas.coffeeShop.repository.entity.StatusOrderEnum;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.service.dto.OrderRequestItemDto;
import com.inAtlas.coffeeShop.service.dto.ProductDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRestControllerTest {

    protected OrderRequestDto orderRequest;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected OrderRequestDto setUpOrder(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId(1L);
        orderRequest.setOrderDateTime(LocalDateTime.now());
        orderRequest.setStatus(StatusOrderEnum.OPEN.toString());
        orderRequest.setTotalQuantity(0L);
        orderRequest.setTotalAmount(0D);
        orderRequest.setTotalDiscount(0D);
       return orderRequest;
    }

    protected OrderRequestDto setUpOrderWithItems(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId(1L);
        orderRequest.setOrderDateTime(LocalDateTime.now());
        orderRequest.setStatus(StatusOrderEnum.OPEN.toString());
        orderRequest.setTotalQuantity(0L);
        orderRequest.setTotalAmount(0D);
        orderRequest.setTotalDiscount(0D);
        orderRequest.setOrderItems(Arrays.asList(setUpOrderRequestItem()));
        return orderRequest;
    }



    protected ProductDto setUpProduct(){
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Latte");
        product.setQuantity(100L);
        product.setPriceUnit(5.3);
        return product;
    }

    protected OrderRequestItemDto setUpOrderRequestItem(){
        OrderRequestItemDto orderRequestItem = new OrderRequestItemDto();
        orderRequestItem.setId(ThreadLocalRandom.current().nextLong());
        orderRequestItem.setPriceUnit(5.3);
        orderRequestItem.setProduct(setUpProduct());
        return orderRequestItem;
    }




}
