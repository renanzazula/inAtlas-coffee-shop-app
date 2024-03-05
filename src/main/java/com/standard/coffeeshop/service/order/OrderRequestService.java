package com.standard.coffeeshop.service.order;


import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.service.dto.PrintReceiptDto;

import java.util.List;

public interface OrderRequestService {

    OrderRequestDto openOrder(String customerId);
    OrderRequestDto addProduct(String customerId, String orderId, String productId);
    OrderRequestDto removeProduct(String customerId, String orderId, String productId);
    OrderRequestDto closeOrder(String customerId, String id);
    OrderRequestDto reopenOrder(String customerId, String id);
    OrderRequestDto delete(String customerId, String id);

    List<OrderRequestDto> getAll(String customerId);
    OrderRequestDto getById(String customerId, String id);
    PrintReceiptDto printOrder(String customerId, String id);

}
