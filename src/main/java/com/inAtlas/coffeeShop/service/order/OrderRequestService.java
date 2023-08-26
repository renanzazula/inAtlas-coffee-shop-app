package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;

import java.util.List;

public interface OrderRequestService {

    OrderRequestDto openOrder();
    OrderRequestDto addProduct(long orderId, long productId);
    OrderRequestDto removeProduct(long orderId, long productId);
    OrderRequestDto closeOrder(long id);
    OrderRequestDto reopenOrder(long id);
    OrderRequestDto delete(long id);

    List<OrderRequestDto> getAll();
    OrderRequestDto getById(long id);


}
