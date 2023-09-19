package com.standard.coffeeShop.service;

import com.standard.coffeeShop.service.dto.DiscountDto;
import com.standard.coffeeShop.service.dto.ProductDto;

public class BaseTest {

    protected static final String PRODUCT_LATTE_TEST = "Latte Test";
    protected static final String PRODUCT_AVAILABLE = "AVAILABLE";
    protected static final long PRODUCT_QUANTITY = 100l;
    protected static final double PRODUCT_PRICE_UNIT = 10.0;

    protected ProductDto setUpProduct(){
        return new ProductDto().builder()
                .name(PRODUCT_LATTE_TEST)
                .description(PRODUCT_LATTE_TEST)
                .status(PRODUCT_AVAILABLE)
                .quantity(PRODUCT_QUANTITY)
                .priceUnit(PRODUCT_PRICE_UNIT).build();
    }

    protected DiscountDto setUpDiscount(){
        return new DiscountDto().builder().build();
//                .name(PRODUCT_LATTE_TEST)
//                .description(PRODUCT_LATTE_TEST)
//                .status(PRODUCT_AVAILABLE)
//                .quantity(PRODUCT_QUANTITY)
//                .priceUnit(PRODUCT_PRICE_UNIT).buildB();
    }

    //configureDiscountProductCombo
    //configureDiscountProduct
    //configureDiscountOrder
}
