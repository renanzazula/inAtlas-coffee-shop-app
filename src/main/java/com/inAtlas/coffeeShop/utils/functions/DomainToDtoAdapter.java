package com.inAtlas.coffeeShop.utils.functions;


import com.inAtlas.coffeeShop.utils.functions.domainToDto.DiscountToDiscountDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.domainToDto.OrderRequestToOrderRequestDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.domainToDto.ProductToProductDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.DiscountDtoToDiscountAdapter;
import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.OrderRequestDtoToOrderRequestAdapter;
import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.ProductDtoToProductAdapter;

public class DomainToDtoAdapter {

    public static final DiscountToDiscountDtoAdapter discountToDiscountDtoAdapter = new DiscountToDiscountDtoAdapter();
    public static final OrderRequestToOrderRequestDtoAdapter orderRequestToOrderRequestDtoAdapter = new OrderRequestToOrderRequestDtoAdapter();
    public static final ProductToProductDtoAdapter productToProductDtoAdapter = new ProductToProductDtoAdapter();

    public DomainToDtoAdapter() {
    }
}
