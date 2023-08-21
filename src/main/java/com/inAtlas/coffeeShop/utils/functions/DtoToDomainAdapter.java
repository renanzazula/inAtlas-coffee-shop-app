package com.inAtlas.coffeeShop.utils.functions;


import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.DiscountDtoToDiscountAdapter;
import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.OrderRequestDtoToOrderRequestAdapter;
import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.ProductDtoToProductAdapter;

public class DtoToDomainAdapter {

    public static final DiscountDtoToDiscountAdapter discountDtoToDiscountAdapter = new DiscountDtoToDiscountAdapter();
    public static final OrderRequestDtoToOrderRequestAdapter orderRequestDtoToOrderRequestAdapter = new OrderRequestDtoToOrderRequestAdapter();
    public static final ProductDtoToProductAdapter productDtoToProductDomainAdapter = new ProductDtoToProductAdapter();

    public DtoToDomainAdapter() {
    }
}
