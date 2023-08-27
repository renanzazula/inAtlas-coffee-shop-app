package com.inAtlas.coffeeShop.utils.functions;


import com.inAtlas.coffeeShop.utils.functions.dtoToDomain.*;

public class DtoToDomainAdapter {

    public static final DiscountDtoToDiscountAdapter discountDtoToDiscountAdapter = new DiscountDtoToDiscountAdapter();
    public static final OrderRequestDtoToOrderRequestAdapter orderRequestDtoToOrderRequestAdapter = new OrderRequestDtoToOrderRequestAdapter();
    public static final ProductDtoToProductAdapter productDtoToProductDomainAdapter = new ProductDtoToProductAdapter();
    public static final ProductDtoToPrintMenuAdapter productDtoToPrintMenuAdapter = new ProductDtoToPrintMenuAdapter();
    public static final ProductDtoToProductItemOrderAdapter productDtoToProductItemOrderAdapter = new ProductDtoToProductItemOrderAdapter();
    public static final OrderRequestItemDtoToOrderRequestItemAdapter orderRequestItemDtoToOrderRequestItemAdapter = new OrderRequestItemDtoToOrderRequestItemAdapter();
    public static final PrintReceiptDtoToPrintReceiptAdapter printReceiptDtoToPrintReceiptAdapter = new PrintReceiptDtoToPrintReceiptAdapter();
    public static final PrintReceiptItemDtoToPrintReceiptItemAdapter printReceiptItemDtoToPrintReceiptItemAdapter = new PrintReceiptItemDtoToPrintReceiptItemAdapter();
    public DtoToDomainAdapter() {
    }
}
