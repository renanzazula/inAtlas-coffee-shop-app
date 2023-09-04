package com.standard.coffeeShop.utils.functions;


import com.standard.coffeeShop.utils.functions.domainToDto.DiscountToDiscountDtoAdapter;
import com.standard.coffeeShop.utils.functions.domainToDto.OrderRequestToOrderRequestDtoAdapter;
import com.standard.coffeeShop.utils.functions.domainToDto.ProductToProductDtoAdapter;

public class DomainToDtoAdapter {

    public static final DiscountToDiscountDtoAdapter discountToDiscountDtoAdapter = new DiscountToDiscountDtoAdapter();
    public static final OrderRequestToOrderRequestDtoAdapter orderRequestToOrderRequestDtoAdapter = new OrderRequestToOrderRequestDtoAdapter();
    public static final ProductToProductDtoAdapter productToProductDtoAdapter = new ProductToProductDtoAdapter();

    public DomainToDtoAdapter() {
    }
}
