package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;

import java.util.List;

public interface OrderRequestService {

    /**
     * Create a new order
     * Start a new order if status OPEN
     *
     * @param obj
     * @return
     */
    OrderRequestDto createNewOrder();
    OrderRequestDto addNewProduct(Long orderId, Long productId, Long quantity);
    OrderRequestDto removeProduct(Long orderId, Long productId, Long quantity);
    OrderRequestDto closeOrder(Long id);


    /**
     * Change status to INATIVE
     * @param id
     */
    OrderRequestDto delete(Long id);

    List<OrderRequestDto> getAll();

    OrderRequestDto getById(Long id);



}
