package com.inAtlas.coffeeShop.utils.functions;


import com.inAtlas.coffeeShop.utils.functions.entityToDto.DiscountEntityToDiscountDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.entityToDto.OrderRequestEntityToOrderRequestDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.entityToDto.ProductEntityToProductDtoAdapter;

public class EntityToDtoAdapter {

    public EntityToDtoAdapter() {
    }

    public static final ProductEntityToProductDtoAdapter productEntityToProductDtoAdapter = new ProductEntityToProductDtoAdapter();
    public static final OrderRequestEntityToOrderRequestDtoAdapter orderRequestEntityToOrderRequestDtoAdapter = new OrderRequestEntityToOrderRequestDtoAdapter();
    public static final DiscountEntityToDiscountDtoAdapter discountEntityToDiscountDtoAdapter = new DiscountEntityToDiscountDtoAdapter();
}
