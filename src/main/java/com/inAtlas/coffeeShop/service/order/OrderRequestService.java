package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;

import java.util.List;

public interface OrderRequestService {

    OrderRequestDto add(OrderRequestDto obj);
    OrderRequestDto update(Long id, OrderRequestDto obj);
    void delete(Long id);
    List<OrderRequestDto> get();
    OrderRequestDto getById(Long id);

}
