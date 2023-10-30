package com.standard.coffeeShop.utils.functions;


import com.standard.coffeeShop.utils.functions.entityToDto.*;

public class EntityToDtoAdapter {

    public EntityToDtoAdapter() {
    }

    public static final AuthorityEntityToAuthorityDtoAdapter authorityEntityToAuthorityDtoAdapter = new AuthorityEntityToAuthorityDtoAdapter();
    public static final UserEntityToUserDtoAdapter userEntityToUserDtoAdapter = new UserEntityToUserDtoAdapter();
    public static final ProductEntityToProductDtoAdapter productEntityToProductDtoAdapter = new ProductEntityToProductDtoAdapter();
    public static final DiscountEntityToDiscountDtoAdapter discountEntityToDiscountDtoAdapter = new DiscountEntityToDiscountDtoAdapter();
    public static final OrderRequestEntityToOrderRequestDtoAdapter orderRequestEntityToOrderRequestDtoAdapter = new OrderRequestEntityToOrderRequestDtoAdapter();
    public static final OrderRequestItemEntityToOrderRequestItemDtoAdapter orderRequestItemEntityToOrderRequestItemDtoAdapter = new OrderRequestItemEntityToOrderRequestItemDtoAdapter();

}
