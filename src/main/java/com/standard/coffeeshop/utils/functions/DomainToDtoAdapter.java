package com.standard.coffeeshop.utils.functions;


import com.standard.coffeeshop.utils.functions.domainToDto.DiscountToDiscountDtoAdapter;
import com.standard.coffeeshop.utils.functions.domainToDto.OrderRequestToOrderRequestDtoAdapter;
import com.standard.coffeeshop.utils.functions.domainToDto.ProductToProductDtoAdapter;

public class DomainToDtoAdapter {

    public static final DiscountToDiscountDtoAdapter discountToDiscountDtoAdapter = new DiscountToDiscountDtoAdapter();
    public static final OrderRequestToOrderRequestDtoAdapter orderRequestToOrderRequestDtoAdapter = new OrderRequestToOrderRequestDtoAdapter();
    public static final ProductToProductDtoAdapter productToProductDtoAdapter = new ProductToProductDtoAdapter();

    public DomainToDtoAdapter() {
    }
}
