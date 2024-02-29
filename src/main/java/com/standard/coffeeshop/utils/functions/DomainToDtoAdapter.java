package com.standard.coffeeshop.utils.functions;


import com.standard.coffeeshop.utils.functions.domain.DiscountToDiscountDtoAdapter;
import com.standard.coffeeshop.utils.functions.domain.OrderRequestToOrderRequestDtoAdapter;
import com.standard.coffeeshop.utils.functions.domain.ProductToProductDtoAdapter;

public class DomainToDtoAdapter {

    private DomainToDtoAdapter() {
    }

    public static final DiscountToDiscountDtoAdapter discountToDiscountDtoAdapter = new DiscountToDiscountDtoAdapter();
    public static final OrderRequestToOrderRequestDtoAdapter orderRequestToOrderRequestDtoAdapter = new OrderRequestToOrderRequestDtoAdapter();
    public static final ProductToProductDtoAdapter productToProductDtoAdapter = new ProductToProductDtoAdapter();

}
