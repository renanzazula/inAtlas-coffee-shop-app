package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.repository.discount.DiscountRepository;
import com.inAtlas.coffeeShop.repository.entity.*;
import com.inAtlas.coffeeShop.repository.order.OrderRequestRepository;
import com.inAtlas.coffeeShop.repository.product.ProductRepository;
import com.inAtlas.coffeeShop.service.dto.DiscountDto;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.utils.Constants;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;


    public OrderRequestServiceImpl(OrderRequestRepository orderRequestRepository, ProductRepository productRepository, DiscountRepository discountRepository) {
        this.orderRequestRepository = orderRequestRepository;
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    @Transactional
    public OrderRequestDto createNewOrder() {
        OrderRequestEntity entity = new OrderRequestEntity();
        entity.setTotalAmount(0D);
        entity.setTotalQuantity(0L);
        entity.setTotalDiscount(0D);
        entity.setStatus(StatusOrderEnum.OPEN);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.save(entity));
    }

    @Override
    @Transactional
    public OrderRequestDto addProduct(long orderId, long productId) {
        OrderRequestEntity orderRequest = orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(Constants.PRODUCT_NOT_FOUND + productId));

        OrderRequestItemEntity orderRequestItem = new OrderRequestItemEntity();
        orderRequestItem.setProduct(productRepository.getById(productId));
        orderRequestItem.setPriceUnit(productRepository.getById(productId).getPriceUnit());
        orderRequest.addOrderItem(orderRequestItem);
        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.saveAndFlush(orderRequest);

        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
        orderRequestEntityTotals.setTotalAmount(calculateSumAmount(orderRequestEntityTotals.getOrderItems()));

        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(orderRequestEntityTotals));
    }


    @Override
    @Transactional
    public OrderRequestDto removeProduct(long orderId, long productId) {
        OrderRequestEntity orderRequestToUpdate = orderRequestRepository.getById(orderId);

        Optional<OrderRequestItemEntity> itemProductToRemove =
                Optional.ofNullable(orderRequestToUpdate
                        .getOrderItems()
                        .stream()
                        .findFirst()
                        .filter(orderHasProduct -> orderHasProduct.getProduct().getId() == (productId))
                        .orElseThrow(() -> new IllegalArgumentException(Constants.PRODUCT_NOT_FOUND + productId)));

        orderRequestToUpdate.removeOrderItem(itemProductToRemove.get());
        OrderRequestEntity orderRequestToUpdated = orderRequestRepository.saveAndFlush(orderRequestToUpdate);

        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.getById(orderId);
        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
        orderRequestEntityTotals.setTotalAmount(calculateMinusAmount(orderRequestEntityTotals.getOrderItems(), orderRequestEntityTotals.getTotalAmount()));
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(orderRequestToUpdated);
    }

    @Override
    @Transactional
    public OrderRequestDto closeOrder(long orderId) {
        orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);

        orderRequestEntity.setTotalQuantity(calculateTotalQuantity(orderRequestEntity.getOrderItems()));
        orderRequestEntity.setTotalAmount(calculateSumAmount(orderRequestEntity.getOrderItems()));

        DiscountDto discountDto = calculateTotalDiscount(orderId);
        orderRequestEntity.setTotalDiscount(discountDto != null ? discountDto.getDiscount() : 0D);

        orderRequestEntity.setStatus(StatusOrderEnum.CLOSE);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(orderRequestEntity));
    }

    @Override
    @Transactional
    public OrderRequestDto delete(long orderId) {
        orderRequestRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(Constants.ORDER_NOT_FOUND + orderId));
        OrderRequestEntity entity = orderRequestRepository.getById(orderId);
        entity.setStatus(StatusOrderEnum.DELETE);
        orderRequestRepository.save(entity);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(entity));

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRequestDto> getAll() {
        return orderRequestRepository.findAll()
                .stream()
                .map(EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRequestDto getById(long orderId) {
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(Constants.ORDER_NOT_FOUND + orderId)));
    }

    private long calculateTotalQuantity(List<OrderRequestItemEntity> orderHasProducts) {
        return orderHasProducts.size();
    }

    private Double calculateSumAmount(List<OrderRequestItemEntity> orderHasProducts) {
        return orderHasProducts.stream().mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit()).sum();
    }

    private Double calculateMinusAmount(List<OrderRequestItemEntity> orderHasProducts, double currentAmount) {
        return currentAmount - orderHasProducts
                .stream()
                .mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit())
                .min().orElse(0);
    }


    private DiscountDto calculateTotalDiscount(long orderId) {
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);

        // get product from order
        List<ProductEntity> allProductsFromOrder = orderRequestEntity.getOrderItems().stream().map(OrderRequestItemEntity::getProduct).collect(Collectors.toList());

        // get discount from db
        List<DiscountEntity> discountListFromDb = discountRepository.findAllByToDate(new Date());

        List<DiscountDto> listOfDiscountsToApply = new ArrayList<>();
        for (int i = 0; i < discountListFromDb.size(); i++) {
            DiscountEntity discount = discountListFromDb.get(i);
            if(discount.getDiscountItems().size() == allProductsFromOrder.size()){
                DiscountDto discountsToApply = new DiscountDto();
                discountsToApply.setDiscount((discount.getDiscount() / 100) * orderRequestEntity.getTotalAmount());
                listOfDiscountsToApply.add(discountsToApply);
            }
        }

        return listOfDiscountsToApply.stream()
                .max(Comparator.comparingDouble(DiscountDto::getDiscount))
                .orElse(null);
    }

    private Double calculatePromotionDiscount(OrderRequestEntity orderRequest) {
        return 10D;
    }

}
