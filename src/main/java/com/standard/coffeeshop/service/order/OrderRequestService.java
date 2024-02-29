package com.standard.coffeeshop.service.order;


import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.service.dto.PrintReceiptDto;

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
    PrintReceiptDto printOrder(long id);

}
