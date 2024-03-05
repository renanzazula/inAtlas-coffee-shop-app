package com.standard.coffeeshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.coffeeshop.repository.entity.StatusOrderEnum;
import com.standard.coffeeshop.service.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class AbstractRestControllerTest {

    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    protected OrderRequestDto orderRequestDto;
    protected PrintReceiptDto printReceiptDto;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected OrderRequestDto setUpOrderDto(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId("1");
        orderRequest.setOrderDateTime(LocalDateTime.now());
        orderRequest.setStatus(StatusOrderEnum.OPEN.toString());
        orderRequest.setTotalQuantity(0L);
        orderRequest.setTotalAmount(0D);
        orderRequest.setTotalDiscount(0D);
       return orderRequest;
    }

    protected OrderRequestDto setUpOrderWithItemsDto(){
        OrderRequestDto orderRequest = new OrderRequestDto();
        orderRequest.setId("1");
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
        product.setId("1");
        product.setName("Latte");
        product.setQuantity(100L);
        product.setPriceUnit(5.3);
        return product;
    }

    protected OrderRequestItemDto setUpOrderRequestItemDto(){
        OrderRequestItemDto orderRequestItem = new OrderRequestItemDto();
        orderRequestItem.setId(String.valueOf(ThreadLocalRandom.current().nextLong()));
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
