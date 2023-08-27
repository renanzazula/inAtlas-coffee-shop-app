package com.inAtlas.coffeeShop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inAtlas.coffeeShop.repository.entity.StatusOrderEnum;
import com.inAtlas.coffeeShop.service.dto.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRestControllerTest {

    protected OrderRequestDto orderRequestDto;
    protected PrintReceiptDto printReceiptDto;


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected OrderRequestDto setUpOrderDto(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId(1L);
        orderRequest.setOrderDateTime(LocalDateTime.now());
        orderRequest.setStatus(StatusOrderEnum.OPEN.toString());
        orderRequest.setTotalQuantity(0L);
        orderRequest.setTotalAmount(0D);
        orderRequest.setTotalDiscount(0D);
       return orderRequest;
    }

    protected OrderRequestDto setUpOrderWithItemsDto(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId(1L);
        orderRequest.setOrderDateTime(LocalDateTime.now());
        orderRequest.setStatus(StatusOrderEnum.OPEN.toString());
        orderRequest.setTotalQuantity(0L);
        orderRequest.setTotalAmount(0D);
        orderRequest.setTotalDiscount(0D);
        orderRequest.setOrderItems(Arrays.asList(setUpOrderRequestItemDto()));
        return orderRequest;
    }

    protected ProductDto setUpProductDto(){
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Latte");
        product.setQuantity(100L);
        product.setPriceUnit(5.3);
        return product;
    }

    protected OrderRequestItemDto setUpOrderRequestItemDto(){
        OrderRequestItemDto orderRequestItem = new OrderRequestItemDto();
        orderRequestItem.setId(ThreadLocalRandom.current().nextLong());
        orderRequestItem.setPriceUnit(5.3);
        orderRequestItem.setProduct(setUpProductDto());
        return orderRequestItem;
    }

    protected PrintReceiptDto setUpPrintReceiptDto(){
        PrintReceiptDto printReceiptDto = new PrintReceiptDto();
        printReceiptDto.setTotal(12.0);
        printReceiptDto.setReceiptItems(Arrays.asList(setUpPrintReceiptItemDto()));
        return printReceiptDto;
    }

    protected PrintReceiptItemDto setUpPrintReceiptItemDto(){
        PrintReceiptItemDto printReceiptItemDto = new PrintReceiptItemDto();
        printReceiptItemDto.setTotal(12.0);
        printReceiptItemDto.setUnitPrice(4.0);
        printReceiptItemDto.setProductName("Espresso");
        printReceiptItemDto.setAmount(3);
        return printReceiptItemDto;
    }


}
